package com.example.domain;

import java.util.List;

/**
 * Domain entity representing a Refreshment Point in the system.
 * This class belongs to the Domain Layer (Enterprise Business Rules).
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String type;
    private List<String> amenities;
    private double rating;

    public RefreshmentPoint() {}

    public RefreshmentPoint(String id, String name, String location, String type, List<String> amenities, double rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.amenities = amenities;
        this.rating = rating;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Checks if the refreshment point is active based on business rules.
     * Assumption: A refreshment point is active if it has a rating >= 0.
     * In a real scenario, this could involve more complex logic.
     *
     * @return true if the refreshment point is considered active
     */
    public boolean isActive() {
        return rating >= 0;
    }
}