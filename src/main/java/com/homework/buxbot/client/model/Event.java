package com.homework.buxbot.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@JsonDeserialize(using = EventDeserializer.class)
public final class Event {

    @JsonProperty("t")
    private final String type;

    @JsonProperty("body")
    private final Body body;

    @JsonCreator
    public Event(
            @JsonProperty(value = "t", required = true) final String type,
            @JsonProperty(value = "body", required = true) final Body body
    ) {
        this.type = type;
        this.body = body;
    }
}
