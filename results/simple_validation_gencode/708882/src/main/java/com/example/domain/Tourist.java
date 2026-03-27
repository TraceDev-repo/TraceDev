package com.example.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents a Tourist entity in the system.
 * Contains core tourist information and details.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Map<String, Object> otherDetails;

    /**
     * Default constructor.
     */
    public Tourist() {
        this.otherDetails = new HashMap<>();
    }

    /**
     * Constructor with core tourist data.
     * @param id the tourist's unique identifier
     * @param name the tourist's name
     * @param email the tourist's email
     * @param phoneNumber the tourist's phone number
     */
    public Tourist(String id, String name, String email, String phoneNumber) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String newPhone) {
        this.phoneNumber = newPhone;
    }

    /**
     * Updates additional details for the tourist.
     * @param details a map of key-value pairs to update
     */
    public void updateDetails(Map<String, Object> details) {
        if (details != null) {
            this.otherDetails.putAll(details);
        }
    }
}