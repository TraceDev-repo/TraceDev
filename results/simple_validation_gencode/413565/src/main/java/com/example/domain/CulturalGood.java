package com.example.domain;

import java.util.Date;
import java.util.Map;

/**
 * Represents a Cultural Good entity.
 * Manages core attributes and validation.
 */
public class CulturalGood {
    private String id;
    private String name;
    private String description;
    private String category;
    private String location;
    private Date lastModifiedDate;

    public CulturalGood() {}

    public CulturalGood(String id, String name, String description, String category, String location, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Date getLastModifiedDate() { return lastModifiedDate; }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    /**
     * Updates the cultural good with new data.
     * @param updatedData Map containing field-value pairs.
     */
    public void updateData(Map<String, Object> updatedData) {
        if (updatedData.containsKey("name")) {
            this.name = (String) updatedData.get("name");
        }
        if (updatedData.containsKey("description")) {
            this.description = (String) updatedData.get("description");
        }
        if (updatedData.containsKey("category")) {
            this.category = (String) updatedData.get("category");
        }
        if (updatedData.containsKey("location")) {
            this.location = (String) updatedData.get("location");
        }
        if (updatedData.containsKey("lastModifiedDate")) {
            this.lastModifiedDate = (Date) updatedData.get("lastModifiedDate");
        }
    }

    /**
     * Validates the cultural good data.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty();
    }
}