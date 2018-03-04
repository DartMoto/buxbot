package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FailedBody implements Body {
    @JsonProperty("message")
    private final String message;
    @JsonProperty("developerMessage")
    private final String developerMessage;
    @JsonProperty("errorCode")
    private final String errorCode;

    @JsonCreator
    public FailedBody(
            @JsonProperty("message") String message,
            @JsonProperty("developerMessage") String developerMessage,
            @JsonProperty("errorCode") String errorCode) {
        this.message = message;
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
    }

}
