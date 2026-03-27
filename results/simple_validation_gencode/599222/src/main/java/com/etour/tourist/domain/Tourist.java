package com.etour.tourist.domain;

import com.etour.tourist.dto.TouristDTO;
import java.util.Map;

/**
 * Domain entity representing a tourist.
 */
public class Tourist {
    private String id;
    private String name;
    private String contactInfo;
    private Map<String, Object> otherDetails;

    public Tourist(String id, String name, String contactInfo, Map<String, Object> otherDetails) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.otherDetails = otherDetails;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Map<String, Object> getOtherDetails() {
        return otherDetails;
    }

    /**
     * Converts the Tourist entity to a TouristDTO.
     */
    public TouristDTO getTouristDTO() {
        return new TouristDTO(this);
    }
}