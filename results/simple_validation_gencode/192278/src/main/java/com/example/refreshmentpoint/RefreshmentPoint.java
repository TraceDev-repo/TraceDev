package com.example.refreshmentpoint;

/**
 * Entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private String pointId;
    private String name;
    private String location;

    public RefreshmentPoint(String pointId, String name, String location) {
        this.pointId = pointId;
        this.name = name;
        this.location = location;
    }

    public String getPointId() {
        return pointId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}