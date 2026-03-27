package com.example.infrastructure.pointofrest;

import com.example.application.ports.PointOfRestService;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter for fetching data from the point of rest.
 */
public class PointOfRestServiceImpl implements PointOfRestService {
    @Override
    public Map<String, Object> fetchConventionRequestData(String conventionId) {
        // Simulate fetching data from an external service
        Map<String, Object> data = new HashMap<>();
        data.put("conventionId", conventionId);
        data.put("requestDate", new java.util.Date());
        data.put("status", "pending");
        System.out.println("Fetched data from point of rest for convention: " + conventionId);
        return data;
    }
}