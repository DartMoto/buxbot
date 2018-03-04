package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public final class Trade {
    @JsonProperty("id")
    private final String id;
    @JsonProperty("positionId")
    private final String positionId;
    @JsonProperty("profitAndLoss")
    private final BigMoney profitAndLoss;
    @JsonProperty("product")
    private final Product product;
    @JsonProperty("investingAmount")
    private final BigMoney investingAmount;
    @JsonProperty("price")
    private final BigMoney price;
    @JsonProperty("leverage")
    private final Integer leverage;
    @JsonProperty("direction")
    private final TradeDirection direction;
    @JsonProperty("type")
    private final TradeType type;
    @JsonProperty("dateCreated")
    private final DateTime dateCreated;

    @JsonCreator
    public Trade(@JsonProperty("id") String id, @JsonProperty("positionId") String positionId, @JsonProperty("profitAndLoss") BigMoney profitAndLoss, @JsonProperty("product") Product product, @JsonProperty("investingAmount") BigMoney investingAmount, @JsonProperty("price") BigMoney price, @JsonProperty("leverage") Integer leverage, @JsonProperty("direction") TradeDirection direction, @JsonProperty("type") TradeType type) {
        this.id = id;
        this.positionId = positionId;
        this.profitAndLoss = profitAndLoss;
        this.product = product;
        this.investingAmount = investingAmount;
        this.price = price;
        this.leverage = leverage;
        this.direction = direction;
        this.type = type;
        this.dateCreated = DateTime.now();
    }
}
