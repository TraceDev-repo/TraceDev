package com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Convention in the domain.
 * Contains a list of RefreshmentPoints.
 */
public class Convention {
    private String id;
    private String name;
    private List<RefreshmentPoint> refreshmentPoints;

    public Convention(String id, String name) {
        this.id = id;
        this.name = name;
        this.refreshmentPoints = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<RefreshmentPoint> getRefreshmentPoints() {
        return refreshmentPoints;
    }

    /**
     * Adds a refreshment point to the convention.
     * @param point the refreshment point to add
     */
    public void addRefreshmentPoint(RefreshmentPoint point) {
        refreshmentPoints.add(point);
    }
}