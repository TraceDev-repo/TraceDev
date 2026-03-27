package com.example.ui;

import com.example.domain.RefreshmentPoint;

/**
 * Data Transfer Object for presenting refreshment point details.
 */
public class RefreshmentPointDetailsDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String contactInfo;

    public RefreshmentPointDetailsDTO(RefreshmentPoint point) {
        this.id = point.getId();
        this.name = point.getName();
        this.location = point.getLocation();
        this.description = point.getDescription();
        this.contactInfo = point.getContactInfo();
    }

    public RefreshmentPointDetailsDTO(Long id, String name, String location, String description, String contactInfo) {
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

    /**
     * Returns a formatted string with all details.
     */
    public String getDetails() {
        return String.format(
            "ID: %d\nName: %s\nLocation: %s\nDescription: %s\nContact: %s",
            id, name, location, description, contactInfo
        );
    }
}