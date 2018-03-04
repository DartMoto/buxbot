package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class BigMoney {
    @JsonProperty(value = "currency")
    private final String currency;
    @JsonProperty(value = "decimals")
    private final int decimalPlaces;
    @JsonProperty(value = "amount")
    private final BigDecimal amount;

    @JsonCreator
    public BigMoney(@JsonProperty(value = "currency", required = true) String currency, @JsonProperty(value = "decimals", required = true) int decimalPlaces, @JsonProperty(value = "amount", required = true) BigDecimal amount) {
        this.currency = currency;
        this.decimalPlaces = decimalPlaces;
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    @JsonProperty("decimals")
    public int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigMoney of(String amount) {
        return new BigMoney(this.currency, this.decimalPlaces, new BigDecimal(amount));
    }
}
