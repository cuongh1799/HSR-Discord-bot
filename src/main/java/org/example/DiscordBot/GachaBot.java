package org.example.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Random;

public class GachaBot extends ListenerAdapter implements EventListener {
    public static void main(String[] args) {

    String token = "";

    JDA jda = JDABuilder.createLight(token,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT,
                    GatewayIntent.GUILD_MEMBERS)
            .addEventListeners(new GachaBot())
            .setActivity(Activity.playing("Gachaing"))
            .build();
        jda.updateCommands().addCommands(
            Commands.slash("roll", "Roll a number from 1 - 100"),
            Commands.slash("long", "godzilla stuff"),
            Commands.slash("path", "Let Ruan Mei choose a path for you"),
            Commands.slash("ruanmei", "schizo about ruanmei"),
            Commands.slash("hesitatetopull", "Let Silver Wolf enlighten you")
        ).queue();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if(event.getMessage().getContentRaw().startsWith("!stfu")){
            event.getChannel().sendMessage("Shut the fuck up").queue();
        }
    }
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        long time = System.currentTimeMillis();

        String[] path = {"Preservation", "Remembrance", "Nihility", "Abundance", "The Hunt", "Destruction", "Elation", "Pro gamer"};
        String copypasta = "What the fuck is actually fucking wrong with you? You actually think some smelly fucking monkey could beat a literal fucking Dragon god? Your stupid ass is so lucky I cant reach through my phone and beat the ever loving shit out of you. What the fuck is king kong even going to do? Sling shit at him? Are you fucking kidding me? Godzilla has the ranged advantage with his atomic breath and the aquatic advantage. The second Kong gets into a ranged fight or they go into water its fucking over. I highly doubt kong could beat godzilla even if he didn't have atomic breath. People who think Kong will actually fucking win deserve to be sent to some fucking Island so society can finally prosper without being plagued by retards. If Kong wins I will actually go in to a fit of rage im not sure if I could even recover from how mad it would make me. TL,DR Kong cucks can actually eat shit and I hope they all learn to not be retarded and believe in Godzilla like the god he is.";
        String ruanmeiBeautiful = "OMG \uD83D\uDE0D\uD83D\uDE0D\uD83D\uDE0D Ruan Mei is so hot \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25 I can't even \uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D She has the most beautiful hair \uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F\uD83D\uDC87\u200D♀\uFE0F ever, it's like a silver river \uD83C\uDF0A\uD83C\uDF0A\uD83C\uDF0A and her eyes \uD83D\uDC40\uD83D\uDC40\uD83D\uDC40 are like blue jewels \uD83D\uDC8E\uD83D\uDC8E\uD83D\uDC8E that shine brighter than the sun ☀\uFE0F☀\uFE0F☀\uFE0F Her skin is so smooth and soft \uD83E\uDD70\uD83E\uDD70\uD83E\uDD70 and her body is so perfect \uD83E\uDD24\uD83E\uDD24\uD83E\uDD24 She wears a white coat \uD83E\uDD7C\uD83E\uDD7C\uD83E\uDD7C that shows how smart \uD83E\uDDE0\uD83E\uDDE0\uD83E\uDDE0 and awesome \uD83D\uDE0E\uD83D\uDE0E\uD83D\uDE0E she is, and a blue scarf \uD83E\uDDE3\uD83E\uDDE3\uD83E\uDDE3 that makes her look even more cute \uD83E\uDD7A\uD83E\uDD7A\uD83E\uDD7A She is the best scientist \uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA ever, she made the Simulated Universe \uD83C\uDF0C\uD83C\uDF0C\uD83C\uDF0C with her friends \uD83D\uDE4C\uD83D\uDE4C\uD83D\uDE4C She is the most amazing woman \uD83D\uDC83\uD83D\uDC83\uD83D\uDC83 in the whole Honkai: Star Rail universe \uD83C\uDF0E\uD83C\uDF0E\uD83C\uDF0E and I love her so much \uD83D\uDC95\uD83D\uDC95\uD83D\uDC95\n";
        File silverwolfpull = new File("src/main/java/org/example/DiscordBot/silverwolfpull.mov");

        switch (event.getName()) {

            case "long":
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(copypasta, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "roll":
                Random rand = new Random();
                int upperbound = 100;
                int int_random = rand.nextInt(upperbound);
                String reply = "Rolled: " + int_random;
                event.reply(reply).setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(reply, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "path":
                Random rand2 = new Random();
                int upperbound2 = 7;
                int int_random2 = rand2.nextInt(upperbound2);
                String reply2 = "Rolled: " + path[int_random2];
                event.reply("").setEphemeral(false) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(reply2, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "ruanmei":
                event.reply("").setEphemeral(false)
                        .flatMap(v ->
                                event.getHook().editOriginalFormat(ruanmeiBeautiful, System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "hesitatetopull":
                FileUpload  silverhesitate = FileUpload.fromData(silverwolfpull);
                event.reply("").setEphemeral(false)
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Just pull buh buh lmao kekw LULA", System.currentTimeMillis() - time)
                        ).queue();//
                event.getChannel().sendFiles(silverhesitate).queue();

                break;
        }
    }
}
