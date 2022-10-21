package com.doodle.listeners;

import com.doodle.Bot;
import com.doodle.game.GameManager;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Commands extends ListenerAdapter {

    GameManager gm = new GameManager();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getMessage().getAuthor();
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        String answerMessage = "Hey " + user.getName() + ", Im here to play Wordle Game with you!";
        String playMessage = "OK, Let's play!";

        if(args[0].equalsIgnoreCase(Bot.PREFIX+"info")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(answerMessage).queue();
        }
        if(args[0].equalsIgnoreCase(Bot.PREFIX + "play")){
            if(!GameManager.running){
                GameManager.running = true;
                event.getChannel().sendMessage(gm.run(0)).queue();
            } else {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Gioco già iniziato").queue();
            }

        }
        if(args[0].equalsIgnoreCase(Bot.PREFIX + "exit")){
            if(GameManager.running){
                GameManager.running = false;
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Gioco terminato").queue();
            } else {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Gioco non iniziato").queue();
            }

        }

    }
}
