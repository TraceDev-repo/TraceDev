package com.example.infrastructure;

import com.example.domain.CulturalObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock client for simulating ETOUR server API calls.
 */
public class EtourApiClient {
    public List<CulturalObject> executeQuery(SearchCriteria criteria) {
        // Simulate network delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Return dummy data
        List<CulturalObject> results = new ArrayList<>();
        results.add(new CulturalObject("1", "Ancient Vase", "A beautiful ancient vase", "Artifact", "Rome"));
        results.add(new CulturalObject("2", "Medieval Tapestry", "Woven tapestry from 14th century", "Art", "Paris"));
        return results;
    }

    public boolean sendHeartbeat() {
        // Simulate heartbeat success
        return true;
    }
}