package com.example.domain;

/**
 * Core domain entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String contactInfo;

    public RefreshmentPoint() {}

    public RefreshmentPoint(Long id, String name, String location, String description, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}