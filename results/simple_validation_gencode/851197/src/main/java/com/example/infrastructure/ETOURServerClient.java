package com.example.infrastructure;

/**
 * Utility class for communicating with the ETOUR Server.
 */
public class ETOURServerClient {
    private String connectionUrl;

    public ETOURServerClient(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String fetchCulturalGoodData(int id) {
        // Simulate fetching data from server
        // In a real implementation, this would make an HTTP/API call
        System.out.println("Fetching data for cultural good id: " + id + " from " + connectionUrl);
        // Simulate JSON/XML response
        return "{\"id\":" + id + ", \"name\":\"Sample Cultural Good\", \"description\":\"A description\", \"location\":\"Rome\", \"type\":\"MONUMENT\"}";
    }

    public String fetchAllCulturalGoodsData() {
        // Added to satisfy requirement REQ-SEQ-001
        System.out.println("Fetching all cultural goods data from " + connectionUrl);
        // Simulate JSON/XML response containing an array
        return "[{\"id\":1, \"name\":\"Colosseum\", \"type\":\"MONUMENT\"}, {\"id\":2, \"name\":\"David\", \"type\":\"ARTIFACT\"}]";
    }

    public boolean isConnected() {
        // Simulate connection check
        // In a real implementation, this would perform a network check
        return true;
    }
}