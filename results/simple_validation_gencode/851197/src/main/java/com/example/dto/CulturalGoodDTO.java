package com.example.dto;

import com.example.domain.CulturalGoodType;

/**
 * Data Transfer Object for basic Cultural Good information.
 */
public class CulturalGoodDTO {
    private int id;
    private String name;
    private CulturalGoodType type;

    public CulturalGoodDTO() {}

    public CulturalGoodDTO(int id, String name, CulturalGoodType type) {
        this.id = id;
        this.name = name;
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

    public CulturalGoodType getType() {
        return type;
    }

    public void setType(CulturalGoodType type) {
        this.type = type;
    }
}