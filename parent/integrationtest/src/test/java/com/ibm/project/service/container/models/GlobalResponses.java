package com.ibm.project.service.container.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalResponses {

    private final Map<String, Object> globalResponses = new HashMap<>();

    public void addToGlobalResponses(String key, Object value) {
        globalResponses.put(key, value);
    }

    public <T> T getFromGlobalResponses(String key) {
        return (T) globalResponses.get(key);
    }
}
