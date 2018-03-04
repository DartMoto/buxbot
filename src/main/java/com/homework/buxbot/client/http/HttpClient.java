package com.homework.buxbot.client.http;

public interface HttpClient {
    void sellOrder(String positionId);

    String buyOrder();
}
