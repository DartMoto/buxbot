package com.homework.buxbot.client.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void testQuoteModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{\"t\" : \"trading.quote\"," +
                "\"body\": {" +
                "\"securityId\": \"sb26493\"," +
                "\"currentPrice\": \"10692.3\"" +
                "}}";
        Event event = mapper.readValue(jsonInString, Event.class);
        assertEquals("trading.quote", event.getType());
        assertEquals("sb26493", ((QuoteBody) event.getBody()).getSecurityId());
        assertEquals(10692.3, ((QuoteBody) event.getBody()).getCurrentPrice(), 0);
    }

    @Test
    public void testFailModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{" +
                "\"t\": \"connect.failed\"," +
                "\"body\": {" +
                "\"developerMessage\": \"Missing JWT Access Token in request\"," +
                "\"errorCode\": \"RTF_002\"" +
                "}}";
        Event event = mapper.readValue(jsonInString, Event.class);
        assertEquals("connect.failed", event.getType());
        assertEquals("Missing JWT Access Token in request", ((FailedBody) event.getBody()).getDeveloperMessage());
        assertEquals("RTF_002", ((FailedBody) event.getBody()).getErrorCode());
    }

    @Test
    public void testSuccessModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{\"body\":{\"userId\":\"bb0cda2b-a10e-4ed3-ad5a-0f82b4c152c4\"," +
                "\"sessionId\":\"9ae34e11-bc85-4fc9-8eb8-6bc4bd6e6982\"," +
                "\"time\":1512230145965}," +
                "\"t\":\"connect.connected\"}";
        Event event = mapper.readValue(jsonInString, Event.class);
        assertEquals("connect.connected", event.getType());
        assertEquals("bb0cda2b-a10e-4ed3-ad5a-0f82b4c152c4", ((SuccessBody) event.getBody()).getUserId());
        assertEquals("9ae34e11-bc85-4fc9-8eb8-6bc4bd6e6982", ((SuccessBody) event.getBody()).getSessionId());
        assertEquals(1512230145965L, ((SuccessBody) event.getBody()).getTime().getMillis());
    }

}