package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Simple in-memory cache for refreshment points.
 * This class belongs to the Infrastructure Layer.
 */
public class RefreshmentPointCache {
    private final Map<String, List<RefreshmentPoint>> cache = new HashMap<>();

    /**
     * Retrieves cached points for the given key.
     *
     * @param key the cache key
     * @return an Optional containing cached points if available
     */
    public Optional<List<RefreshmentPoint>> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    /**
     * Stores points in the cache with the given key.
     *
     * @param key the cache key
     * @param points the points to cache
     */
    public void put(String key, List<RefreshmentPoint> points) {
        cache.put(key, points);
        System.out.println("Cached points for key: " + key);
    }

    /**
     * Invalidates (removes) the cached entry for the given key.
     *
     * @param key the cache key
     */
    public void invalidate(String key) {
        cache.remove(key);
        System.out.println("Invalidated cache for key: " + key);
    }

    /**
     * Clears the entire cache.
     */
    public void clear() {
        cache.clear();
        System.out.println("Cache cleared.");
    }
}