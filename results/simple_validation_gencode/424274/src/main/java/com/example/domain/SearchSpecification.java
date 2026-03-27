package com.example.domain;

import java.util.List;

/**
 * Domain search specification used by the domain and application layers.
 * This class belongs to the Domain Layer.
 */
public class SearchSpecification {
    private String location;
    private String pointType;
    private double ratingThreshold;
    private List<String> requiredAmenities;

    public SearchSpecification() {}

    public SearchSpecification(String location, String pointType, double ratingThreshold, List<String> requiredAmenities) {
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
}