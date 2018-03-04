package com.homework.buxbot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.homework.buxbot.client.websocket.MessageHandler;
import com.homework.buxbot.client.websocket.WsClientEndpoint;
import com.homework.buxbot.config.ApplicationConfig;
import com.homework.buxbot.config.DependencyModule;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

@Slf4j
public class Application {
    private final ApplicationConfig applicationConfig;
    private final Injector injector;

    private Application() {
        injector = Guice.createInjector(new DependencyModule());
        applicationConfig = injector.getInstance(ApplicationConfig.class);
    }

    private void runTradingBot() {
        try {
            final WsClientEndpoint clientEndPoint = new WsClientEndpoint(new URI(applicationConfig.getWsUrl()));
            MessageHandler messageHandler = injector.getInstance(MessageHandler.class);
            clientEndPoint.addMessageHandler(messageHandler);
            clientEndPoint.sendMessage("{\"subscribeTo\": [\"trading.product." + applicationConfig.getProductId() + "\"],\"unsubscribeFrom\": []}");
            new Scanner(System.in).nextLine();
        } catch (URISyntaxException e) {
            log.error("Bad formatted websocket url", e);
        }
    }


    public static void main(String[] args) {
        final Application app = new Application();
        app.runTradingBot();
    }


}
