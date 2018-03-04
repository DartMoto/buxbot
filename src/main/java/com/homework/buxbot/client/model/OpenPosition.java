package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OpenPosition
{
    private final String productId;
    private final BigMoney investingAmount;
    private final Integer leverage;
    private final TradeDirection direction;
    private final String source;

    @JsonCreator
    public OpenPosition(@JsonProperty(value="productId", required=true) String productId, @JsonProperty(value="investingAmount", required=true) BigMoney investingAmount, @JsonProperty(value="leverage", required=true) Integer leverage, @JsonProperty(value="direction", required=true) TradeDirection direction, @JsonProperty(value="source", required=true) String source)
    {
        this.productId = productId;
        this.investingAmount = investingAmount;
        this.leverage = leverage;
        this.direction = direction;
        this.source = source;
    }


}

