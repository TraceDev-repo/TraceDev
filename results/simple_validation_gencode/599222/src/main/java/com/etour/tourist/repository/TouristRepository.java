package com.etour.tourist.repository;

import com.etour.tourist.domain.Tourist;
import com.etour.tourist.domain.TouristNotFoundException;
import java.util.Map;
import java.util.HashMap;

/**
 * Repository implementation with simple caching.
 */
public class TouristRepository implements ITouristRepository {
    private ServerEtourDataSource dataSource;
    private Map<String, Tourist> cache;

    public TouristRepository() {
        this.dataSource = new ServerEtourDataSource();
        this.cache = new HashMap<>();
    }

    /**
     * Retrieves a tourist by ID, checking cache first.
     */
    @Override
    public Tourist findById(String id) {
        // Step 5b: Check cache explicitly
        Tourist cachedTourist = getFromCache(id);
        if (cachedTourist != null) {
            return cachedTourist;
        }

        // Cache miss - fetch from data source
        try {
            Map<String, Object> rawData = dataSource.fetchTouristData(id);
            // Step 8a and 8b: Map raw data to Tourist entity
            Tourist tourist = mapDataToTourist(id, rawData);
            // Step 8c: Cache the tourist
            putInCache(id, tourist);
            return tourist;
        } catch (RuntimeException e) {
            // Determine if it's a connection error or tourist not found
            String errorMsg = e.getMessage();
            String detailedMessage;
            if (errorMsg != null && errorMsg.contains("Connection Error")) {
                detailedMessage = "Connection interrupted: Unable to reach server";
            } else {
                detailedMessage = "Tourist not found with ID: " + id;
            }
            throw new TouristNotFoundException(id, detailedMessage);
        }
    }

    /**
     * Retrieves a tourist from the cache.
     */
    public Tourist getFromCache(String id) {
        return cache.get(id);
    }

    /**
     * Stores a tourist in the cache.
     */
    public void putInCache(String id, Tourist tourist) {
        cache.put(id, tourist);
    }

    /**
     * Maps raw data map to a Tourist entity.
     */
    private Tourist mapDataToTourist(String id, Map<String, Object> rawData) {
        String name = (String) rawData.get("name");
        String contactInfo = (String) rawData.get("contactInfo");
        @SuppressWarnings("unchecked")
        Map<String, Object> otherDetails = (Map<String, Object>) rawData.get("otherDetails");
        return new Tourist(id, name, contactInfo, otherDetails);
    }
}