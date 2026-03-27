package com.example.domain;

/**
 * Domain entity representing a cultural heritage.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;

    public CulturalHeritage(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the cultural heritage as deleted (domain logic).
     */
    public void delete() {
        // Perform any domain-specific deletion logic here.
        System.out.println("[DOMAIN] Cultural heritage " + id + " marked as deleted.");
    }
}