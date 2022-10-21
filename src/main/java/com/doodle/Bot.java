package com.doodle;

import com.doodle.listeners.PlayerChat;
import com.doodle.listeners.Commands;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Bot {

    public static final String PREFIX = "!";

    private final ShardManager shardManager;

    public Bot() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(System.getenv().get("TOKEN"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Auto Tedesca Glöryyy"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();

        shardManager.addEventListener(new Commands(), new PlayerChat());
    }

    public ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args) {

        try {
            Bot bot = new Bot();
        } catch (LoginException e) {
            System.out.println("ERROR: provided bot TOKEN is invalid");
        }

    }

}
