package com.example.dto;

import com.example.domain.CulturalGoodType;
import java.util.Map;

/**
 * Data Transfer Object for detailed Cultural Good information.
 */
public class CulturalGoodDetailDTO {
    private int id;
    private String name;
    private String description;
    private String location;
    private CulturalGoodType type;
    private Map<String, String> additionalInfo;

    public CulturalGoodDetailDTO() {}

    public CulturalGoodDetailDTO(int id, String name, String description, String location, CulturalGoodType type, Map<String, String> additionalInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CulturalGoodType getType() {
        return type;
    }

    public void setType(CulturalGoodType type) {
        this.type = type;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}