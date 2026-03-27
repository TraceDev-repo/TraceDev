package com.etour.domain;

/**
 * Represents a refreshment point (turning point) where banners can be placed.
 */
public class TurningPoint {
    private String id;
    private String name;
    private String location;
    private String description;

    public TurningPoint(String id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}