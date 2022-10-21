package com.doodle.listeners;

import com.doodle.game.GameManager;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PlayerChat extends ListenerAdapter {

    GameManager gm = new GameManager();

    private static int attempts = 5;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        String msg = event.getMessage().getContentRaw().replaceAll("\\s+","");

        if(msg.contains("!")){
            System.out.println("[DEBUG]: Command");
        } else if(msg.length() == 5 && !(user.isBot())) {
            GameManager.guess = msg;
            event.getChannel().sendMessage(gm.run(1)).queue();
        }
    }
}
