package com.example.application.dto;

import com.example.domain.Tourist;
import java.util.Map;

/**
 * Data Transfer Object for Tourist entity.
 * Used to transfer tourist data between layers.
 */
public class TouristDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * Default constructor.
     */
    public TouristDTO() {
    }

    /**
     * Constructs a DTO from a Tourist entity.
     * @param tourist the tourist entity to convert
     */
    public TouristDTO(Tourist tourist) {
        if (tourist != null) {
            this.id = tourist.getId();
            this.name = tourist.getName();
            this.email = tourist.getEmail();
            this.phoneNumber = tourist.getPhoneNumber();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Converts this DTO to a Tourist entity.
     * @return a new Tourist entity with DTO data
     */
    public Tourist toEntity() {
        Tourist tourist = new Tourist();
        tourist.setName(this.name);
        tourist.setEmail(this.email);
        tourist.setPhoneNumber(this.phoneNumber);
        // Note: ID is typically set by the repository
        return tourist;
    }
}