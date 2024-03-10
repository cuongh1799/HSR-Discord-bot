package org.example.DiscordBot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.DiscordBot.mediaPlayer.GuildMusicManager;

import java.util.HashMap;
import java.util.Map;

public class MusicBot extends ListenerAdapter  {

    private static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private static final Map<Long, GuildMusicManager> musicManagerMap = new HashMap<>();

    // Constructor
    public MusicBot(){
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public static AudioPlayerManager getPlayerManager() {
        return playerManager;
    }

    public static Map<Long, GuildMusicManager> getMusicManagerMap() {
        return musicManagerMap;
    }

    // Get Guild(Server) musicManager
    private static synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
        long guildId = Long.parseLong(guild.getId());

        // Get the GuildMusicManager by finding on the musicManagers map based on get(guildID) key
        GuildMusicManager musicManager = musicManagerMap.get(guildId);

        // If get key and the key doesn't have any musicManager, create one
        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagerMap.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    // Connect bot to the voice channel
    private static void connectToFirstVoiceChannel(AudioManager audioManager) {
        if (!audioManager.isConnected()) {
            for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
                audioManager.openAudioConnection(voiceChannel);
                break;
            }
        }
    }

    // Connects to the voice channel and queue track
    private static void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
        connectToFirstVoiceChannel((AudioManager) guild.getAudioManager());

        musicManager.scheduler.queue(track);
    }

    // Load URL and add to queue
    public static void loadAndPlay(final TextChannel channel, final String trackUrl) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());

        // Hmm this is quite interesting
        // All of the commands below are for the `new AudioLoadResultHandler()`
        // Just click on `AudioLoadResultHandler()` implementation LULE
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Adding to queue " + track.getInfo().title).queue();

                play(channel.getGuild(), musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                channel.sendMessage("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();

                play(channel.getGuild(), musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
    }

    public static void skipTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.nextTrack();

        channel.sendMessage("Skipped to next track.").queue();
    }

    public static void pauseTrack(TextChannel channel) throws InterruptedException {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.onPlayerPause(musicManager.getPlayer());
        channel.sendMessage("Pause!").queue();
    }

    public static void unpauseTrack(TextChannel channel) throws InterruptedException {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.onPlayerResume(musicManager.getPlayer());
        channel.sendMessage("Unpause!").queue();
    }

    public static void listTrack(TextChannel channel){
        String list = "";
        int count = 0;
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        for(AudioTrack e : musicManager.scheduler.getBlockingQueue()){
            list += count + ". " + e.getInfo().title +"\n";
        }
        channel.sendMessage("Here's the current track list: ").queue();
        channel.sendMessage(list).queue();
    }

    public static void EndSession(TextChannel channel){
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        channel.sendMessage("Let us meet again!").queue();
        musicManager.scheduler.EndAll(musicManager.getPlayer());
    }
}
