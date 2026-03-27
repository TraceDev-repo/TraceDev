package com.etour.dto;

import java.util.List;

/**
 * Data Transfer Object for RefreshmentPoint.
 * Used for form data transfer and validation.
 */
public class RefreshmentPointDTO {
    public String id;
    public String name;
    public String address;
    public List<String> amenities;

    /**
     * Validates the DTO fields.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        // Amenities can be empty
        return true;
    }
}