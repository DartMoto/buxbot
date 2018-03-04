package com.homework.buxbot.client.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EventDeserializer extends JsonDeserializer<Event> {
    public Event deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        JsonNode jsonNode = node.get("t");
        if (null == jsonNode) return null;
        final String type = jsonNode.asText();
        Body body;
        switch (type) {
            case "connect.connected":
                body = oc.treeToValue(node.get("body"), SuccessBody.class);
                break;
            case "connect.failed":
                body = oc.treeToValue(node.get("body"), FailedBody.class);
                break;
            case "trading.quote":
                body = oc.treeToValue(node.get("body"), QuoteBody.class);
                break;
            default:
                throw new RuntimeException("Unrecognized type of message");

        }
        return new Event(type, body);
    }
}
