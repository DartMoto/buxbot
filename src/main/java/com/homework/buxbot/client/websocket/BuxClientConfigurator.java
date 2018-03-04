package com.homework.buxbot.client.websocket;

import javax.websocket.ClientEndpointConfig;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.homework.buxbot.client.constants.Constants.AUTH_TOKEN;

public class BuxClientConfigurator extends ClientEndpointConfig.Configurator {

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        headers.put("Accept-Language", Collections.singletonList("nl-NL,en;q=0.8"));
        headers.put("Authorization", Collections.singletonList(AUTH_TOKEN));
        headers.put("Accept", Collections.singletonList("application/json"));
    }

}