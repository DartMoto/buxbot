package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class QuoteBody implements Body{
    @JsonProperty("securityId")
    private final String securityId;
    @JsonProperty("currentPrice")
    private final Double currentPrice;
    @JsonProperty("timeStamp")
    private final DateTime timeStamp;

    @JsonCreator
    public QuoteBody(
            @JsonProperty("securityId") String securityId,
            @JsonProperty("currentPrice") Double currentPrice,
            @JsonProperty("timeStamp") DateTime timeStamp) {
        this.securityId = securityId;
        this.currentPrice = currentPrice;
        this.timeStamp = timeStamp;
    }
}
