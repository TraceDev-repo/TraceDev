package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Client for communicating with the eTour server.
 * This class belongs to the Infrastructure Layer.
 */
public class EServerApiClient {
    /**
     * Searches for refreshment points on the eTour server based on criteria.
     *
     * @param criteria the search criteria
     * @return a list of matching refreshment points
     * @throws ConnectionException if the connection to the server is interrupted
     */
    public List<RefreshmentPoint> searchPoints(SearchCriteria criteria) throws ConnectionException {
        // Simulate connection check
        if (!isConnectionActive()) {
            throw new ConnectionException("Connection to eTour server is interrupted");
        }

        // Simulate server request and response
        System.out.println("Sending search request to eTour server with criteria: " +
                "location=" + criteria.getLocation() +
                ", type=" + criteria.getPointType());

        // Simulate server processing time
        try {
            Thread.sleep(500); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return mock data for demonstration
        return generateMockPoints(criteria);
    }

    /**
     * Fetches detailed information for a specific refreshment point.
     *
     * @param id the point identifier
     * @return the refreshment point details
     * @throws ConnectionException if the connection to the server is interrupted
     */
    public RefreshmentPoint fetchPointDetails(String id) throws ConnectionException {
        // Simulate connection check
        if (!isConnectionActive()) {
            throw new ConnectionException("Connection to eTour server is interrupted");
        }

        System.out.println("Fetching point details from eTour server for id: " + id);

        // Simulate server request
        try {
            Thread.sleep(300); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return mock point
        return new RefreshmentPoint(id, "Refreshment Point " + id, "Location " + id,
                "Type A", List.of("WiFi", "Restroom"), 4.5);
    }

    /**
     * Checks if the connection to the eTour server is active.
     * In a real implementation, this would perform an actual connectivity check.
     *
     * @return true if connection is active (simulated as always true for demonstration)
     */
    private boolean isConnectionActive() {
        // For demonstration, we simulate a connection failure 20% of the time
        return Math.random() > 0.2;
    }

    /**
     * Generates mock refreshment points based on search criteria for demonstration.
     *
     * @param criteria the search criteria
     * @return a list of mock refreshment points
     */
    private List<RefreshmentPoint> generateMockPoints(SearchCriteria criteria) {
        List<RefreshmentPoint> points = new ArrayList<>();

        // Generate 3 mock points
        for (int i = 1; i <= 3; i++) {
            String id = "RP" + i;
            String name = "Refreshment Point " + i;
            String location = criteria.getLocation() != null ? criteria.getLocation() + " Area" : "Unknown Location";
            String type = criteria.getPointType() != null ? criteria.getPointType() : "General";
            List<String> amenities = criteria.getRequiredAmenities() != null && !criteria.getRequiredAmenities().isEmpty()
                    ? criteria.getRequiredAmenities()
                    : List.of("WiFi", "Restroom", "Seating");
            double rating = Math.max(criteria.getRatingThreshold(), 4.0 + (i * 0.2)); // Ensure rating meets threshold

            points.add(new RefreshmentPoint(id, name, location, type, amenities, rating));
        }

        return points;
    }
}