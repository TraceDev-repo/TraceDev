package com.example.infrastructure;

import java.util.Map;

/**
 * External data source adapter for ETOUR server.
 */
public class ETOURServerDataSource {
    /**
     * Fetches refreshment point data from server.
     * @param id the refreshment point ID
     * @return raw data as a map
     */
    public Map<String, Object> fetchRefreshmentPointData(Long id) {
        // Simulate server call; in real implementation would use HTTP client, etc.
        // For simplicity, return a mock map.
        return Map.of(
            "id", id,
            "name", "Refreshment Point " + id,
            "location", "Location " + id,
            "description", "Description for " + id,
            "contactInfo", "Contact " + id
        );
    }

    /**
     * Loads refreshment point data from server (alias for fetch).
     * @param id the refreshment point ID
     * @return raw data as a map
     */
    public Map<String, Object> loadRefreshmentPointData(Long id) {
        // This method is an alias for fetchRefreshmentPointData to align with sequence diagram naming.
        return fetchRefreshmentPointData(id);
    }
}