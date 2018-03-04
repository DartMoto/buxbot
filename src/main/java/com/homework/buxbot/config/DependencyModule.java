package com.homework.buxbot.config;

import com.google.inject.AbstractModule;
import com.homework.buxbot.client.http.ApacheHttpClient;
import com.homework.buxbot.client.http.HttpClient;
import com.homework.buxbot.client.websocket.BuxMessageHandler;
import com.homework.buxbot.client.websocket.MessageHandler;

public class DependencyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HttpClient.class).to(ApacheHttpClient.class);
        bind(MessageHandler.class).to(BuxMessageHandler.class);
    }
}