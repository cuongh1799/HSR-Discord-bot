//package org.example.DiscordBot.commands;
//
//
//import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Random;
//
//public class SlashCommands extends ListenerAdapter{
//    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
//        long time = System.currentTimeMillis();
//
//        String[] path = {"Preservation", "Remembrance", "Nihility", "Abundance", "The Hunt", "Destruction", "Elation", "Pro gamer"};
//        String copypasta = "What the fuck is actually fucking wrong with you? You actually think some smelly fucking monkey could beat a literal fucking Dragon god? Your stupid ass is so lucky I cant reach through my phone and beat the ever loving shit out of you. What the fuck is king kong even going to do? Sling shit at him? Are you fucking kidding me? Godzilla has the ranged advantage with his atomic breath and the aquatic advantage. The second Kong gets into a ranged fight or they go into water its fucking over. I highly doubt kong could beat godzilla even if he didn't have atomic breath. People who think Kong will actually fucking win deserve to be sent to some fucking Island so society can finally prosper without being plagued by retards. If Kong wins I will actually go in to a fit of rage im not sure if I could even recover from how mad it would make me. TL,DR Kong cucks can actually eat shit and I hope they all learn to not be retarded and believe in Godzilla like the god he is.";
//        String ruanmeiBeautiful = "OMG \uD83D\uDE0D\uD83D\uDE0D\uD83D\uDE0D Ruan Mei is so hot \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25 I can't even \uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D She has the most beautiful hair \uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F ever, it's like a silver river \uD83C\uDF0A\uD83C\uDF0A\uD83C\uDF0A and her eyes \uD83D\uDC40\uD83D\uDC40\uD83D\uDC40 are like blue jewels \uD83D\uDC8E\uD83D\uDC8E\uD83D\uDC8E that shine brighter than the sun ☀\uFE0F☀\uFE0F☀\uFE0F Her skin is so smooth and soft \uD83E\uDD70\uD83E\uDD70\uD83E\uDD70 and her body is so perfect \uD83E\uDD24\uD83E\uDD24\uD83E\uDD24 She wears a white coat \uD83E\uDD7C\uD83E\uDD7C\uD83E\uDD7C that shows how smart \uD83E\uDDE0\uD83E\uDDE0\uD83E\uDDE0 and awesome \uD83D\uDE0E\uD83D\uDE0E\uD83D\uDE0E she is, and a blue scarf \uD83E\uDDE3\uD83E\uDDE3\uD83E\uDDE3 that makes her look even more cute \uD83E\uDD7A\uD83E\uDD7A\uD83E\uDD7A She is the best scientist \uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA ever, she made the Simulated Universe \uD83C\uDF0C\uD83C\uDF0C\uD83C\uDF0C with her friends \uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C She is the most amazing woman \uD83D\uDC83\uD83D\uDC83\uD83D\uDC83 in the whole Honkai: Star Rail universe \uD83C\uDF0E\uD83C\uDF0E\uD83C\uDF0E and I love her so much \uD83D\uDC95\uD83D\uDC95\uD83D\uDC95\n";
//
//
//        switch (event.getName()) {
//
//            case "long":
//                            event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat(copypasta, System.currentTimeMillis() - time) // then edit original
//                        ).queue(); // Queue both reply and edit
//                break;
//
//            case "roll":
//                Random rand = new Random();
//                int upperbound = 100;
//                int int_random = rand.nextInt(upperbound);
//                String reply = "Rolled: " + int_random;
//                event.reply(reply).setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat(reply, System.currentTimeMillis() - time) // then edit original
//                        ).queue(); // Queue both reply and edit
//                break;
//
//            case "path":
//                Random rand2 = new Random();
//                int upperbound2 = 7;
//                int int_random2 = rand2.nextInt(upperbound2);
//                String reply2 = "Rolled: " + path[int_random2];
//                event.reply("").setEphemeral(false) // reply or acknowledge
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat(reply2, System.currentTimeMillis() - time) // then edit original
//                        ).queue(); // Queue both reply and edit
//                break;
//
//            case "ruanmei":
//                event.reply("").setEphemeral(false)
//                        .flatMap(v ->
//                                event.getHook().editOriginalFormat(ruanmeiBeautiful, System.currentTimeMillis() - time) // then edit original
//                        ).queue(); // Queue both reply and edit
//                break;
//        }
//    }
//}
