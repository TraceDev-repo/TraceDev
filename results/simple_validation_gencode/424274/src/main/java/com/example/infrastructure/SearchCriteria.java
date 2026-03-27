package com.example.infrastructure;

import java.util.List;

/**
 * Search criteria used by the infrastructure layer to query external services.
 * This class belongs to the Infrastructure Layer.
 */
public class SearchCriteria {
    private String location;
    private String pointType;
    private double ratingThreshold;
    private List<String> requiredAmenities;

    public SearchCriteria() {}

    public SearchCriteria(String location, String pointType, double ratingThreshold, List<String> requiredAmenities) {
        this.location = location;
        this.pointType = pointType;
        this.ratingThreshold = ratingThreshold;
        this.requiredAmenities = requiredAmenities;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public double getRatingThreshold() {
        return ratingThreshold;
    }

    public void setRatingThreshold(double ratingThreshold) {
        this.ratingThreshold = ratingThreshold;
    }

    public List<String> getRequiredAmenities() {
        return requiredAmenities;
    }

    public void setRequiredAmenities(List<String> requiredAmenities) {
        this.requiredAmenities = requiredAmenities;
    }

    /**
     * Generates a cache key based on the search criteria.
     *
     * @return a string representation suitable for caching
     */
    public String toCacheKey() {
        return "SearchCriteria_" + location + "_" + pointType + "_" + ratingThreshold + "_" + requiredAmenities.hashCode();
    }
}