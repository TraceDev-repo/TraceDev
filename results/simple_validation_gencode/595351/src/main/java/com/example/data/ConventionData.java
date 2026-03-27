package com.example.data;

// Data class representing uploaded convention data
public class ConventionData {
    private int agencyId;
    private int selectedRestaurantId;
    private String uploadedData;
    
    // Getters and setters
    public int getAgencyId() {
        return agencyId;
    }
    
    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
    
    public int getSelectedRestaurantId() {
        return selectedRestaurantId;
    }
    
    public void setSelectedRestaurantId(int selectedRestaurantId) {
        this.selectedRestaurantId = selectedRestaurantId;
    }
    
    public String getUploadedData() {
        return uploadedData;
    }
    
    public void setUploadedData(String uploadedData) {
        this.uploadedData = uploadedData;
    }
}