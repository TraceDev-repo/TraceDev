package com.example.ui;

import java.util.List;

/**
 * UI component for displaying search results.
 */
public class SearchResultsUI {
    private RefreshmentPointUICoordinator coordinator;

    public void setCoordinator(RefreshmentPointUICoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Displays a list of refreshment point DTOs.
     */
    public void displayResults(List<RefreshmentPointDetailsDTO> results) {
        // In a real UI, this would update the view.
        System.out.println("Displaying " + results.size() + " results.");
        for (RefreshmentPointDetailsDTO dto : results) {
            System.out.println(" - " + dto.getName() + " (ID: " + dto.getId() + ")");
        }
    }

    /**
     * Called when a user selects an item from the results list.
     * @param selectedId the ID of the selected refreshment point
     */
    public void onItemSelected(Long selectedId) {
        System.out.println("Item selected with ID: " + selectedId);
        if (coordinator != null) {
            coordinator.notifySelection(selectedId);
        }
    }
}