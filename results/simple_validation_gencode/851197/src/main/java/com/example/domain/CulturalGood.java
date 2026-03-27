package com.example.domain;

import com.example.dto.CulturalGoodDTO;
import com.example.dto.CulturalGoodDetailDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity representing a Cultural Good.
 */
public class CulturalGood {
    private int id;
    private String name;
    private String description;
    private String location;
    private CulturalGoodType type;

    public CulturalGood() {}

    public CulturalGood(int id, String name, String description, String location, CulturalGoodType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
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

    /**
     * Creates a CulturalGoodDTO from this entity.
     * This method satisfies the model mapping relationship.
     */
    public CulturalGoodDTO toDTO() {
        return new CulturalGoodDTO(id, name, type);
    }

    /**
     * Creates a CulturalGoodDetailDTO from this entity.
     * This method satisfies the model mapping relationship.
     */
    public CulturalGoodDetailDTO toDetailDTO() {
        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("year", "Unknown"); // example additional info
        return new CulturalGoodDetailDTO(id, name, description, location, type, additionalInfo);
    }
}