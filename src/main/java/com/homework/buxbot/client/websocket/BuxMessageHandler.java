package com.homework.buxbot.client.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.homework.buxbot.client.http.ApacheHttpClient;
import com.homework.buxbot.client.model.Event;
import com.homework.buxbot.client.model.QuoteBody;
import com.homework.buxbot.config.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Singleton
public class BuxMessageHandler implements MessageHandler {

    @Inject
    private ApacheHttpClient httpClient;
    @Inject
    private ApplicationConfig config;

    private String positionId;

    private ObjectMapper mapper = new ObjectMapper();
    public void handleMessage(String message) {
        log.debug(message);
        try {
            Event event = mapper.readValue(message, Event.class);
            if (null != event && "trading.quote".equalsIgnoreCase(event.getType())) {
                QuoteBody body = (QuoteBody) event.getBody();
                double currentPrice = body.getCurrentPrice();
                if (positionIdIsNotEmpty()) {
                    if (currentPrice <= config.getLowLimitSell() | currentPrice >= config.getUpLimitSell()) {
                        httpClient.sellOrder(positionId);
                        positionId = "";
                    }
                } else if (Math.abs((config.getBuyPrice() - currentPrice) / config.getBuyPrice()) < config.getDelta()) { //almost equal
                    positionId = httpClient.buyOrder();
                }
            }
        } catch (IOException e) {
            log.error("Error while parsing JSON", e);
        }
    }

    private boolean positionIdIsNotEmpty() {
        return getPositionId() != null && !getPositionId().isEmpty();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
}
