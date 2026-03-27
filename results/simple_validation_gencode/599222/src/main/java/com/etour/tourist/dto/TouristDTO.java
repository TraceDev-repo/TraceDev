package com.etour.tourist.dto;

import com.etour.tourist.domain.Tourist;
import java.util.Map;

/**
 * Data Transfer Object for tourist information.
 */
public class TouristDTO {
    public String id;
    public String name;
    public String contactInfo;
    public String formattedDetails;

    public TouristDTO(Tourist tourist) {
        this.id = tourist.getId();
        this.name = tourist.getName();
        this.contactInfo = tourist.getContactInfo();
        // Build a formatted string from other details
        Map<String, Object> otherDetails = tourist.getOtherDetails();
        StringBuilder detailsBuilder = new StringBuilder();
        if (otherDetails != null) {
            for (Map.Entry<String, Object> entry : otherDetails.entrySet()) {
                detailsBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("; ");
            }
        }
        this.formattedDetails = detailsBuilder.toString();
    }

    // Getters and setters if needed, though fields are public as per the diagram.
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getFormattedDetails() {
        return formattedDetails;
    }
}