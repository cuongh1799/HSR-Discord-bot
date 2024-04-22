package org.example.DiscordBot;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.example.DiscordBot.mediaPlayer.*;

public class discordbot extends ListenerAdapter implements EventListener {
    public static void main(String[] args) throws Exception {

    String token = "MTE4MjkzMjI1NDI5MzQ5OTk2NA.GHCSGt.yitSuoo5nSOk_AFVReb93m9afCuOrMjhZMuKrg";

    JDA jda = JDABuilder.createLight(token,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT,
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_VOICE_STATES)
            .addEventListeners(new discordbot()) // THIS IS IMPORTANT AS COMMENTING THIS OUT, THE BOT WON'T ABLE TO RECEIVE YOUR MESSAGE
            .setActivity(Activity.customStatus("A luminous crimson light, lurks within the abyss of the world."))
            .build();
        jda.updateCommands().addCommands(
            Commands.slash("roll", "Roll a number from 1 - 100"),
            Commands.slash("long", "godzilla stuff"),
            Commands.slash("path", "Let Ruan Mei choose a path for you"),
            Commands.slash("effort", "Why put so much effort into this project"),
            //Commands.slash("ruanmei", "schizo about ruanmei"),
            Commands.slash("hesitatetopull", "Let Silver Wolf enlighten you"),
            //Commands.slash("link", "Something to hype things up"),
            //Commands.slash("pullruanmei", "All roads lead to Ruan Mei"),
            Commands.slash("goodbye", "Farewell, may the Trailblaze guide us")
            //Commands.slash("play", "testtstetstetse")
        ).queue();
    }

    MusicBot musicbotnew = new MusicBot();
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ", 2);

        switch(command[0]){
            case "!play":
                MusicBot.loadAndPlay((TextChannel) event.getChannel(), command[1]);
                break;
            case "!skip":
                MusicBot.skipTrack((TextChannel) event.getChannel());
                break;
            case "!pause":
                try {
                    MusicBot.pauseTrack((TextChannel) event.getChannel());
                } catch (InterruptedException e) {
                    System.out.println("Failed");
                    throw new RuntimeException(e);
                }
                break;
            case "!unpause":
                try {
                    MusicBot.unpauseTrack((TextChannel) event.getChannel());
                } catch (InterruptedException e) {
                    System.out.println("Failed");
                    throw new RuntimeException(e);
                }
                break;
            case "!list":
                MusicBot.listTrack((TextChannel) event.getChannel());
                break;
            case "!end":
                MusicBot.EndSession((TextChannel) event.getChannel());
                ((TextChannel) event.getChannel()).getGuild().getAudioManager().closeAudioConnection();
                break;
            case "!volume":
                event.getChannel().sendMessage("Current volume is " + MusicBot.viewPlayerVolume((TextChannel) event.getChannel())).queue();
                break;
            case "!acheron":
                event.getChannel().sendMessage("https://projektacheron.vercel.app/").queue();
                break;
            case "!whyeffort":
                String whyeffort = "Behold, the era of mundane pulling is over.\n" +
                        "\n" +
                        "No more shall it be a flavorless, dreary, and uneventful ordeal.\n" +
                        "\n" +
                        "Today marks a revolutionary transformation.\n" +
                        "\n" +
                        "We redefine the essence of pulling day.\n" +
                        "\n" +
                        "Embrace the genesis.\n" +
                        "\n" +
                        "The beacon of change.\n" +
                        "\n" +
                        "Projekt://Acheron.\n" +
                        "\n" +
                        "Let the anticipation begin.";
                event.getChannel().sendMessage(whyeffort).queue();
                break;
        }

//        if ("~play".equals(command[0]) && command.length == 2) {
//            MusicBot.loadAndPlay((TextChannel) event.getChannel(), command[1]);
//        }
//        else if ("~skip".equals(command[0])) {
//            MusicBot.skipTrack((TextChannel) event.getChannel());
//        }
//        else if ("~pause".equals(command[0])) {
//            try {
//                MusicBot.pauseTrack((TextChannel) event.getChannel());
//            } catch (InterruptedException e) {
//                System.out.println("Failed");
//                throw new RuntimeException(e);
//            }
//        }
//        else if("~unpause".equals(command[0])){
//            try {
//                MusicBot.unpauseTrack((TextChannel) event.getChannel());
//            } catch (InterruptedException e) {
//                System.out.println("Failed");
//                throw new RuntimeException(e);
//            }
//        }
//        else if ("~list".equals(command[0])) {
//            MusicBot.listTrack((TextChannel) event.getChannel());
//        }
//        else if ("~end".equals(command[0])) {
//            MusicBot.EndSession((TextChannel) event.getChannel());
//            ((TextChannel) event.getChannel()).getGuild().getAudioManager().closeAudioConnection();
//        }
        super.onMessageReceived(event);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        long time = System.currentTimeMillis();

        String[] path = {"Preservation", "Remembrance", "Nihility", "Abundance", "The Hunt", "Destruction", "Elation", "Pro gamer", "Erudition"};
        String godzilla = "What the fuck is actually fucking wrong with you? You actually think some smelly fucking monkey could beat a literal fucking Dragon god? Your stupid ass is so lucky I cant reach through my phone and beat the ever loving shit out of you. What the fuck is king kong even going to do? Sling shit at him? Are you fucking kidding me? Godzilla has the ranged advantage with his atomic breath and the aquatic advantage. The second Kong gets into a ranged fight or they go into water its fucking over. I highly doubt kong could beat godzilla even if he didn't have atomic breath. People who think Kong will actually fucking win deserve to be sent to some fucking Island so society can finally prosper without being plagued by retards. If Kong wins I will actually go in to a fit of rage im not sure if I could even recover from how mad it would make me. TL,DR Kong cucks can actually eat shit and I hope they all learn to not be retarded and believe in Godzilla like the god he is.";
        String ruanmeiBeautiful = "OMG \uD83D\uDE0D\uD83D\uDE0D\uD83D\uDE0D Ruan Mei is so hot \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25 I can't even \uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D She has the most beautiful hair \uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F ever, it's like a silver river \uD83C\uDF0A\uD83C\uDF0A\uD83C\uDF0A and her eyes \uD83D\uDC40\uD83D\uDC40\uD83D\uDC40 are like blue jewels \uD83D\uDC8E\uD83D\uDC8E\uD83D\uDC8E that shine brighter than the sun ☀\uFE0F☀\uFE0F☀\uFE0F Her skin is so smooth and soft \uD83E\uDD70\uD83E\uDD70\uD83E\uDD70 and her body is so perfect \uD83E\uDD24\uD83E\uDD24\uD83E\uDD24 She wears a white coat \uD83E\uDD7C\uD83E\uDD7C\uD83E\uDD7C that shows how smart \uD83E\uDDE0\uD83E\uDDE0\uD83E\uDDE0 and awesome \uD83D\uDE0E\uD83D\uDE0E\uD83D\uDE0E she is, and a blue scarf \uD83E\uDDE3\uD83E\uDDE3\uD83E\uDDE3 that makes her look even more cute \uD83E\uDD7A\uD83E\uDD7A\uD83E\uDD7A She is the best scientist \uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA ever, she made the Simulated Universe \uD83C\uDF0C\uD83C\uDF0C\uD83C\uDF0C with her friends \uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C She is the most amazing woman \uD83D\uDC83\uD83D\uDC83\uD83D\uDC83 in the whole Honkai: Star Rail universe \uD83C\uDF0E\uD83C\uDF0E\uD83C\uDF0E and I love her so much \uD83D\uDC95\uD83D\uDC95\uD83D\uDC95\n";
        File silverwolfpull = new File("src/main/resources/silverwolfpull.mov");
        File allroadleadstoruanmei = new File("src/main/resources/allroadleadstoruanmei.png");

        switch (event.getName()) {
//            case "play":
//                //String[] command = event.getMessage().getContentRaw().split(" ", 2);
//                String[] command = event.getInteraction().toString().split(" ", 2);
//                //event.reply("the link is " + command[1]).queue();
//                event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat("amgousg" + command[1], System.currentTimeMillis() - time) // then edit original
//                        ).queue();

//            case "link":
//                event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat("insert link here") // then edit original
//                        ).queue();
//                break;

            case "long":
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(godzilla, System.currentTimeMillis() - time) // then edit original
                        ).queue();
                break;

            case "roll":
                Random rand = new Random();
                int upperbound = 100;
                int int_random = rand.nextInt(upperbound);
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Rolled: " + int_random, System.currentTimeMillis() - time) // then edit original
                        ).queue();
                break;

            case "path":
                Random rand2 = new Random();
                int upperbound2 = 8;
                int int_random2 = rand2.nextInt(upperbound2);
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Ruan Mei chose " + path[int_random2] + " !", System.currentTimeMillis() - time) // then edit original
                        ).queue();
                break;

//            case "ruanmei":
//                event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat(ruanmeiBeautiful, System.currentTimeMillis() - time) // then edit original
//                        ).queue();
//                break;

            case "hesitatetopull":
                FileUpload  silverhesitate = FileUpload.fromData(silverwolfpull);
                event.replyFiles(silverhesitate).queue();
                break;

//            case "pullruanmei":
//                FileUpload  allroadstoruanmei = FileUpload.fromData(allroadleadstoruanmei);
//                event.replyFiles(allroadstoruanmei).queue();
//                break;

            case "goodbye":
//                event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat("Let us once again cross roads, in this vast universe.", System.currentTimeMillis() - time) // then edit original
//                        ).queue();
//                try {
//                    wait(10000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                event.getJDA().shutdownNow();
                break;

        }
    }
}
