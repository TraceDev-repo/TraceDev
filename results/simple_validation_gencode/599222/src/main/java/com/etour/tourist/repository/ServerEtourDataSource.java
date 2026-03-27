package com.etour.tourist.repository;

import java.util.Map;
import java.util.HashMap;

/**
 * Simulates the external data source (e.g., a server).
 */
public class ServerEtourDataSource {

    // Simulated database of tourists
    private Map<String, Map<String, Object>> touristData;

    public ServerEtourDataSource() {
        touristData = new HashMap<>();
        // Populate with some sample data
        Map<String, Object> tourist1 = new HashMap<>();
        tourist1.put("name", "John Doe");
        tourist1.put("contactInfo", "john@example.com");
        tourist1.put("otherDetails", Map.of("age", 30, "nationality", "US"));
        touristData.put("T001", tourist1);

        Map<String, Object> tourist2 = new HashMap<>();
        tourist2.put("name", "Jane Smith");
        tourist2.put("contactInfo", "jane@example.com");
        tourist2.put("otherDetails", Map.of("age", 25, "nationality", "UK"));
        touristData.put("T002", tourist2);
    }

    /**
     * Fetches raw tourist data from the server.
     * @param id the tourist ID
     * @return a map of raw data
     */
    public Map<String, Object> fetchTouristData(String id) {
        // Simulate a potential connection error
        if ("errorId".equals(id)) {
            throw new RuntimeException("Connection Error");
        }
        Map<String, Object> data = touristData.get(id);
        if (data == null) {
            throw new RuntimeException("Tourist not found in server");
        }
        // Return a copy to avoid mutation issues
        return new HashMap<>(data);
    }
}