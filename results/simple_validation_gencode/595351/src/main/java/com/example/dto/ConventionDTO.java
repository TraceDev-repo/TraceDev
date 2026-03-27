package com.example.dto;

import java.util.Date;

// Data Transfer Object for convention data
public class ConventionDTO {
    private int id;
    private int agencyId;
    private int derivedFromPointOfRestId;
    private Date dateCreated;
    
    // Default constructor
    public ConventionDTO() {
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getAgencyId() {
        return agencyId;
    }
    
    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
    
    public int getDerivedFromPointOfRestId() {
        return derivedFromPointOfRestId;
    }
    
    public void setDerivedFromPointOfRestId(int derivedFromPointOfRestId) {
        this.derivedFromPointOfRestId = derivedFromPointOfRestId;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}