package com.example.springbootslack.module;

public interface SlackModule {
    void sendService(String message);

    void sendService(String channelName, String message);
}
