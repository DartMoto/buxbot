package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
public class SuccessBody implements Body{
    @JsonProperty("userId")
    private final String userId;
    @JsonProperty("sessionId")
    private final String sessionId;
    @JsonProperty("time")
    private final DateTime time;

    @JsonCreator
    public SuccessBody(
            @JsonProperty("userId") String securityId,
            @JsonProperty("sessionId") String currentPrice,
            @JsonProperty("time") DateTime timeStamp) {
        this.userId = securityId;
        this.sessionId = currentPrice;
        this.time = timeStamp;
    }
}
