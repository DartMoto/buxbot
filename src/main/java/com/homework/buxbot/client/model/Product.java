package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public final class Product {
    @JsonProperty("securityId")
    private final String securityId;
    @JsonProperty("symbol")
    private final String symbol;
    @JsonProperty("displayName")
    private final String displayName;
    @JsonProperty("closingPrice")
    private final BigMoney closingPrice;
    @JsonProperty("currentPrice")
    private BigMoney currentPrice;

    @JsonCreator
    public Product(@JsonProperty("securityId") String securityId, @JsonProperty("symbol") String symbol, @JsonProperty("displayName") String displayName, @JsonProperty("currentPrice") BigMoney currentPrice, @JsonProperty("closingPrice") BigMoney closingPrice) {
        this.securityId = securityId;
        this.symbol = symbol;
        this.displayName = displayName;
        this.currentPrice = currentPrice;
        this.closingPrice = closingPrice;
    }

}
