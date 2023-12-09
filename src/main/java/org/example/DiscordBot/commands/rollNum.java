package org.example.DiscordBot.commands;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class rollNum extends ListenerAdapter{
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        long time = System.currentTimeMillis();
        
        String[] path = {"Preservation", "Remembrance", "Nihility", "Abundance", "The Hunt", "Destruction", "Elation", "Pro gamer"};

        switch (event.getName()) {

            case "long":
                String copypasta = "What the fuck is actually fucking wrong with you? You actually think some smelly fucking monkey could beat a literal fucking Dragon god? Your stupid ass is so lucky I cant reach through my phone and beat the ever loving shit out of you. What the fuck is king kong even going to do? Sling shit at him? Are you fucking kidding me? Godzilla has the ranged advantage with his atomic breath and the aquatic advantage. The second Kong gets into a ranged fight or they go into water its fucking over. I highly doubt kong could beat godzilla even if he didn't have atomic breath. People who think Kong will actually fucking win deserve to be sent to some fucking Island so society can finally prosper without being plagued by retards. If Kong wins I will actually go in to a fit of rage im not sure if I could even recover from how mad it would make me. TL,DR Kong cucks can actually eat shit and I hope they all learn to not be retarded and believe in Godzilla like the god he is.";
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(copypasta, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "roll":
                // Instance of random class
                Random rand = new Random();
                // Setting the upper bound to generate the
                // random numbers in specific range
                int upperbound = 100;
                // Generating random values from 0 - 24
                // using nextInt()
                int int_random = rand.nextInt(upperbound);
                String reply = "Rolled: " + int_random;
                event.reply(reply).setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(reply, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "path":
                // Instance of random class
                Random rand2 = new Random();
                // Setting the upper bound to generate the
                // random numbers in specific range
                int upperbound2 = 7;
                // Generating random values from 0 - 24
                // using nextInt()
                int int_random2 = rand2.nextInt(upperbound2);
                String reply2 = "Rolled: " + path[int_random2];
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(reply2, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
        }
    }
}
