package com.agency.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a refreshment point where banners are displayed.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;

    public RefreshmentPoint() {
    }

    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public List<Banner> getBanners() {
        // In a real implementation, this would fetch from a repository
        return new ArrayList<>();
    }

    public boolean removeBanner(String bannerId) {
        // This method would normally interact with a repository to delete the banner
        // For simplicity, we assume removal is always successful
        return true;
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
}