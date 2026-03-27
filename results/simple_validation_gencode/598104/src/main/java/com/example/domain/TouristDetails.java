package com.example.domain;

/**
 * Contains personal details of a tourist.
 */
public class TouristDetails {
    private String name;
    private String email;

    public TouristDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}