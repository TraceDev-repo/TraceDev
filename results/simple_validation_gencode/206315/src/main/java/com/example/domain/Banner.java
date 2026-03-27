package com.example.domain;

/**
 * Represents a Banner in the domain.
 */
public class Banner {
    private String id;
    private String name;
    private String conventionId;
    private String refreshmentPointId;

    public Banner(String id, String name, String conventionId, String refreshmentPointId) {
        this.id = id;
        this.name = name;
        this.conventionId = conventionId;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }
}