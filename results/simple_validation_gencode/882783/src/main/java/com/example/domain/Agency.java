package com.example.domain;

import com.example.application.ports.PointOfRestService;

/**
 * Represents an Agency which may be a designated point of rest.
 */
public class Agency {
    private String id;
    private String name;
    private boolean designatedPointOfRest;
    private PointOfRestService pointOfRestService; // Used for data fetching

    public Agency(String id, String name, boolean designatedPointOfRest, PointOfRestService pointOfRestService) {
        this.id = id;
        this.name = name;
        this.designatedPointOfRest = designatedPointOfRest;
        this.pointOfRestService = pointOfRestService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDesignatedPointOfRest() {
        return designatedPointOfRest;
    }

    public void setDesignatedPointOfRest(boolean designatedPointOfRest) {
        this.designatedPointOfRest = designatedPointOfRest;
    }

    /**
     * Explicitly checks if agency is designated for activation.
     */
    public boolean isDesignatedForActivation() {
        return designatedPointOfRest;
    }

    /**
     * Fetches a convention request from the point of rest.
     * This unifies point-of-rest concept with data loading responsibility.
     */
    public ConventionRequest fetchConventionRequest(String conventionId) {
        if (pointOfRestService != null) {
            // Use the service to fetch data
            java.util.Map<String, Object> data = pointOfRestService.fetchConventionRequestData(conventionId);
            ConventionRequest request = new ConventionRequest("REQ-" + conventionId, conventionId);
            request.updateExternalData(data);
            request.setStatus(RequestStatus.PROCESSING);
            return request;
        }
        // Simulate fetching data
        ConventionRequest request = new ConventionRequest("REQ-" + conventionId, conventionId);
        request.setStatus(RequestStatus.PROCESSING);
        java.util.Map<String, Object> dummyData = new java.util.HashMap<>();
        dummyData.put("conventionId", conventionId);
        dummyData.put("dummy", true);
        request.updateExternalData(dummyData);
        return request;
    }

    public void setPointOfRestService(PointOfRestService pointOfRestService) {
        this.pointOfRestService = pointOfRestService;
    }
}