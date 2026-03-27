package com.etour.tourist.ui;

import com.etour.tourist.controller.ViewTouristDetailsController;
import com.etour.tourist.dto.TouristDTO;
import com.etour.tourist.usecase.ISearchTouristUseCase;
import com.etour.tourist.domain.TouristNotFoundException;
import java.util.List;

/**
 * The UI component that displays tourist details.
 * It holds the selected tourist ID and renders the tourist card.
 */
public class TouristCardDisplay {
    private String selectedTouristId;
    private ViewTouristDetailsController controller;
    private ISearchTouristUseCase searchTouristUseCase; // For obtaining the tourist list

    public TouristCardDisplay(ViewTouristDetailsController controller, ISearchTouristUseCase searchTouristUseCase) {
        this.controller = controller;
        this.searchTouristUseCase = searchTouristUseCase;
    }

    /**
     * Step 1: Called when the user selects a tourist from the list.
     */
    public void selectTouristFromList(TouristDTO touristDTO) {
        this.selectedTouristId = touristDTO.getId();
    }

    /**
     * Step 2: Internal method to store the selected tourist ID.
     */
    public void storeSelectedTouristId(String id) {
        this.selectedTouristId = id;
    }

    /**
     * Step 3: Explicitly activates the display.
     * Triggers the retrieval and display of tourist details.
     */
    public void activateDisplay() {
        if (selectedTouristId == null) {
            System.out.println("No tourist selected.");
            return;
        }
        try {
            // Step 4: Call controller
            TouristDTO dto = controller.execute(selectedTouristId);
            // Step 13-14: Display the tourist details
            displayTouristDetails(dto);
        } catch (TouristNotFoundException e) {
            // Implement distinct error messages as per sequence diagram alt flow
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("Connection interrupted")) {
                displayErrorMessage("Connection interrupted. Please check your network and try again.");
            } else {
                displayErrorMessage("Tourist not found. Please verify the tourist ID.");
            }
        }
    }

    /**
     * Displays the tourist details on the card.
     */
    public void displayTouristDetails(TouristDTO dto) {
        // In a real UI, this would update the UI components.
        System.out.println("=== Tourist Card ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Contact: " + dto.getContactInfo());
        System.out.println("Details: " + dto.getFormattedDetails());
        System.out.println("===================");
    }

    /**
     * Displays an error message.
     */
    public void displayErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Sets the tourist list (simulates the list obtained from SearchTourist use case).
     * This method actively obtains the tourist list via ISearchTouristUseCase.
     */
    public void setTouristList(List<TouristDTO> touristList) {
        // In a real UI, this would update the list component.
        System.out.println("Tourist list updated with " + touristList.size() + " items.");
        // Additional integration: Optionally call searchTouristUseCase to refresh the list
        // For example:
        // touristList = searchTouristUseCase.searchTourist("");
        // Then update UI components accordingly
    }

    // Getters and setters for testing purposes
    public String getSelectedTouristId() {
        return selectedTouristId;
    }

    public void setSelectedTouristId(String selectedTouristId) {
        this.selectedTouristId = selectedTouristId;
    }
}