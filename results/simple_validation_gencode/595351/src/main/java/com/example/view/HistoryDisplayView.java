package com.example.view;

import com.example.dto.ConventionDTO;
import com.example.data.ConventionData;
import java.util.List;

// Boundary class for displaying convention history and interacting with users
public class HistoryDisplayView {
    
    // Displays a list of convention history
    public void displayHistory(List<ConventionDTO> conventions) {
        System.out.println("=== Convention History ===");
        if (conventions == null || conventions.isEmpty()) {
            System.out.println("No convention history found.");
        } else {
            for (ConventionDTO dto : conventions) {
                System.out.printf("Convention ID: %d, Agency ID: %d, Created: %s%n",
                    dto.getId(), dto.getAgencyId(), dto.getDateCreated());
            }
        }
        System.out.println("========================");
    }
    
    // Shows error messages to the user
    public void showError(String message) {
        System.err.println("ERROR: " + message);
    }
    
    // Prompts the user for convention data upload
    public ConventionData promptForConventionData() {
        // In a real GUI application, this would show a file chooser/dialog
        // For console demonstration, create sample data
        ConventionData data = new ConventionData();
        data.setAgencyId(1); // Sample agency ID
        data.setSelectedRestaurantId(100); // Sample restaurant ID
        data.setUploadedData("Sample convention data content");
        
        System.out.println("Created ConventionData with agency ID: " + data.getAgencyId());
        return data;
    }
}