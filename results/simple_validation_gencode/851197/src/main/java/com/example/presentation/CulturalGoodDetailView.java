package com.example.presentation;

import com.example.dto.CulturalGoodDetailDTO;

/**
 * Boundary class for displaying detailed cultural good information.
 */
public class CulturalGoodDetailView {
    
    public void displayCulturalGoodDetails(CulturalGoodDetailDTO details) {
        System.out.println("=== Cultural Good Details ===");
        System.out.println("ID: " + details.getId());
        System.out.println("Name: " + details.getName());
        System.out.println("Description: " + details.getDescription());
        System.out.println("Location: " + details.getLocation());
        System.out.println("Type: " + details.getType());
        System.out.println("Additional Info: " + details.getAdditionalInfo());
        System.out.println("=============================");
    }

    public void showLoading() {
        System.out.println("Loading details...");
    }

    public void hideLoading() {
        System.out.println("Loading complete.");
    }

    public void showError(String message) {
        System.err.println("Error in Detail View: " + message);
    }
}