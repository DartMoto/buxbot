package com.homework.buxbot.client.websocket;

import com.homework.buxbot.client.http.ApacheHttpClient;
import com.homework.buxbot.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BuxMessageHandlerTest {

    @Mock
    private ApplicationConfig config;
    @Mock
    private ApacheHttpClient httpClient;
    @InjectMocks
    private BuxMessageHandler handler = new BuxMessageHandler();

    @Test
    public void handleMessageBuy() {
        when(config.getBuyPrice()).thenReturn(12173.0);
        when(config.getDelta()).thenReturn(0.00001);
        handler.handleMessage("{\"body\":{\"securityId\":\"sb26493\",\"currentPrice\":\"12173.1\",\"timeStamp\":1512332860335},\"t\":\"trading.quote\"}");
        verify(httpClient).buyOrder();
    }

    @Test
    public void handleMessageSellUp() {
        when(config.getBuyPrice()).thenReturn(12170.0);
        when(config.getUpLimitSell()).thenReturn(12172.0);
        when(config.getDelta()).thenReturn(0.00001);
        handler.setPositionId("eb01da10-37e2-406b-99b3-88fbce916c0c");
        handler.handleMessage("{\"body\":{\"securityId\":\"sb26493\",\"currentPrice\":\"12173.1\",\"timeStamp\":1512332860335},\"t\":\"trading.quote\"}");
        verify(httpClient).sellOrder("eb01da10-37e2-406b-99b3-88fbce916c0c");
    }

    @Test
    public void handleMessageSellLow() {
        when(config.getBuyPrice()).thenReturn(12170.0);
        when(config.getLowLimitSell()).thenReturn(12169.0);
        when(config.getDelta()).thenReturn(0.00001);
        handler.setPositionId("eb01da10-37e2-406b-99b3-88fbce916c0c");
        handler.handleMessage("{\"body\":{\"securityId\":\"sb26493\",\"currentPrice\":\"12168.1\",\"timeStamp\":1512332860335},\"t\":\"trading.quote\"}");
        verify(httpClient).sellOrder("eb01da10-37e2-406b-99b3-88fbce916c0c");
    }
}