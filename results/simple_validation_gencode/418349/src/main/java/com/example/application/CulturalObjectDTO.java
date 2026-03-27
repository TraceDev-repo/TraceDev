package com.example.application;

/**
 * DTO for presenting cultural object data to the UI.
 */
public class CulturalObjectDTO {
    private final String id;
    private final String title;
    private final String description;
    private final String type;

    public CulturalObjectDTO(String id, String title, String description, String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
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

    @Override
    public String toString() {
        return "CulturalObjectDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}