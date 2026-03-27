package com.example.application;

import java.util.List;

/**
 * Query object for searching refreshment points.
 * This class belongs to the Application Layer.
 */
public class SearchRefreshmentPointsQuery {
    private String locationFilter;
    private String typeFilter;
    private double minRating;
    private List<String> amenities;

    public SearchRefreshmentPointsQuery() {}

    public SearchRefreshmentPointsQuery(String locationFilter, String typeFilter, double minRating, List<String> amenities) {
        this.locationFilter = locationFilter;
        this.typeFilter = typeFilter;
        this.minRating = minRating;
        this.amenities = amenities;
    }

    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }

    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}