package com.agency.reporting.domain;

import java.util.List;

/**
 * Represents an agency that has locations.
 */
public class Agency {
    private final String id;
    private final String name;
    private final List<Location> locations; // Association: Agency HAS places list

    public Agency(String id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }
}