package org.example.DiscordBot.mediaPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class schedules tracks for the audio player. It contains the queue of tracks.
 */
public class TrackScheduler extends AudioEventAdapter {
  private final AudioPlayer player;
  private final BlockingQueue<AudioTrack> queueList;

  /**
   * @param player The audio player this scheduler uses
   */
  public TrackScheduler(AudioPlayer player) {
    this.player = player;
    this.queueList = new LinkedBlockingQueue<>();
  }

  /**
   * Add the next track to queue or play right away if nothing is in the queue.
   *
   * @param track The track to play or add to queue.
   */
  public void queue(AudioTrack track) {
    // Calling startTrack with the noInterrupt set to true will start the track only if nothing is currently playing. If
    // something is playing, it returns false and does nothing. In that case the player was already playing so this
    // track goes to the queue instead.
    if (!player.startTrack(track, true)) {
      queueList.offer(track);
    }
  }

  /**
   * Start the next track, stopping the current one if it is playing.
   */
  public void nextTrack() {
    // Start the next track, regardless of if something is already playing or not. In case queue was empty, we are
    // giving null to startTrack, which is a valid argument and will simply stop the player.
    player.startTrack(queueList.poll(), false);
  }

  @Override
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
    // Only start the next track if the end reason is suitable for it (FINISHED or LOAD_FAILED)
    if (endReason.mayStartNext) {
      nextTrack();
    }
  }

  @Override
  // overide the function because in the DefaultAudioPlayer.class it didn't get implemented
  public void onPlayerPause(AudioPlayer player){
    if(!player.isPaused()){
      player.setPaused(true);
    }
  }


  public void onPlayerResume(AudioPlayer player){
    if(player.isPaused()){
      player.setPaused(false);
    }
  }

  public BlockingQueue<AudioTrack> getBlockingQueue(){ return queueList;}

  public void EndAll(AudioPlayer player){
      player.destroy();
  }

  public void setVolume(int volume){
    player.setVolume(volume);
  }

  public int getVolume(){
    return player.getVolume();
  }
}


