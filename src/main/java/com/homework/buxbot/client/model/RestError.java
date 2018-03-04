package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class RestError {
    @JsonProperty("message")
    private final String message;
    @JsonProperty("developerMessage")
    private final String developerMessage;
    @JsonProperty("errorCode")
    private final String errorCode;

    public RestError(String message, String errorCode) {
        this(message, message, errorCode);
    }

    @JsonCreator
    public RestError(@JsonProperty("message") String message, @JsonProperty("developerMessage") String developerMessage, @JsonProperty("errorCode") String errorCode) {
        this.message = message;
        this.developerMessage = developerMessage;
        this.errorCode = errorCode;
    }
}
