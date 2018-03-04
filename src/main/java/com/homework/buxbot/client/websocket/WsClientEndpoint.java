package com.homework.buxbot.client.websocket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.net.URI;

@Slf4j
@ClientEndpoint(configurator = BuxClientConfigurator.class)
public class WsClientEndpoint {
    private Session userSession = null;
    private MessageHandler messageHandler;

    public WsClientEndpoint(final URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            log.error("WsClientEndpoint init error:", e);
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(final Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(final Session userSession, final CloseReason reason) {
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(final String message) {
        if (messageHandler != null) {
            messageHandler.handleMessage(message);
        }
    }

    public void addMessageHandler(final MessageHandler msgHandler) {
        messageHandler = msgHandler;
    }

    public void sendMessage(final String message) {
        userSession.getAsyncRemote().sendText(message);
    }
}