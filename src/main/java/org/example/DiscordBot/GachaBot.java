package org.example.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.DiscordBot.commands.rollNum;
import org.example.DiscordBot.mediaPlayer.AudioPlayerSendHandler;

import java.util.Random;

public class GachaBot extends ListenerAdapter {
    public static void main(String[] args) {

    String token = "MTE4MjkzMjI1NDI5MzQ5OTk2NA.GL9e1-.Pq-IDusO6Fxx8XQOAWryxM5s_cKi5s5awOmRfw";

    JDA jda = JDABuilder.createLight(token)
            .addEventListeners(new GachaBot())
            .setActivity(Activity.playing("gachaing"))
            .build();
            jda.addEventListener(new rollNum());
    }
}
