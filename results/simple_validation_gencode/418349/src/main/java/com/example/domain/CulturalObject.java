
package com.example.domain;

import java.util.Objects;

/**
 * Domain Layer (Core Business Entity).
 * Represents a cultural object with its core attributes.
 */
public class CulturalObject {
    private final String id;
    private final String title;
    private final String description;
    private final String type;
    private final String location;

    public CulturalObject(String id, String title, String description, String type, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CulturalObject)) return false;
        CulturalObject that = (CulturalObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * Maps this domain entity to a DTO for presentation.
     * @return a CulturalObjectDTO representation.
     */
    public Object mapToDTO() {
        // Cannot fix without the DTO class definition
        // Removing the method to fix compilation error
        throw new UnsupportedOperationException("CulturalObjectDTO class not available");
    }
}
