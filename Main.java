package com.rocketseat.createUrlShortner;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Map<String, String> handleRequest(Map<String, Object> Input, Context context) {
        String body = Input.get("body").toString();

        Map<String, String> bodyMap;
        try {
            bodyMap = objectMapper.readValue(body, Map.class);
        } catch (Exception exception) {
            throw new RuntimeException("Error parsing Json body: " + exception.getMessage(), exception);
        }

        String originalUrl = bodyMap.get("originalUrl");
        String expirationTime = bodyMap.get("expirationTime");

        String shortUrlCode = UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> response = new HashMap<>();
        response.put("code", shortUrlCode);
        response.put("originalUrl", originalUrl);
        response.put("expirationTime", expirationTime);



        return null;
    }
}