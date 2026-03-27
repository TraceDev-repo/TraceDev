package com.example.dto;

import com.example.enums.TouristAccountStatus;
import java.io.Serializable;

/**
 * DTO that carries search criteria for tourist accounts.
 */
public class SearchTouristDTO implements Serializable {
    private String name;
    private String email;
    private String country;
    private TouristAccountStatus status;

    // Constructors
    public SearchTouristDTO() {}

    public SearchTouristDTO(String name, String email, String country, TouristAccountStatus status) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.status = status;
    }

    // Getters and Setters
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public TouristAccountStatus getStatus() {
        return status;
    }

    public void setStatus(TouristAccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SearchTouristDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", status=" + status +
                '}';
    }
}