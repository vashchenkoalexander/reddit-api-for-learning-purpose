package com.example.redditbotjava17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redditbot.RedditBotClass;

@SpringBootApplication
public class RedditBotJava17Application {

    public static void main(String[] args) {
        SpringApplication.run(RedditBotJava17Application.class, args);
        RedditBotClass.main(args);
    }

}
