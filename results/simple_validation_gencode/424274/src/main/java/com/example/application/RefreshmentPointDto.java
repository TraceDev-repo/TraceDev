package com.example.application;

import java.util.List;

/**
 * Data Transfer Object for RefreshmentPoint.
 * Used to transfer data between layers, especially to the presentation layer.
 */
public class RefreshmentPointDto {
    private String id;
    private String name;
    private String location;
    private String type;
    private List<String> amenities;
    private double rating;

    public RefreshmentPointDto() {}

    public RefreshmentPointDto(String id, String name, String location, String type, List<String> amenities, double rating) {
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
}