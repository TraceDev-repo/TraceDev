package com.agency.reporting.infrastructure.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory cache.
 */
public class Cache {
    private final Map<String, Object> store = new HashMap<>();

    public Object get(String key) {
        return store.get(key);
    }

    public void put(String key, Object value) {
        store.put(key, value);
    }
}