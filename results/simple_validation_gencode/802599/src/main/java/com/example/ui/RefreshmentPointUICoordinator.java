package com.example.ui;

import java.util.List;

/**
 * Coordinates between UI components and controllers.
 */
public class RefreshmentPointUICoordinator {
    private ViewRefreshmentPointDetailsController detailsController;

    public RefreshmentPointUICoordinator(ViewRefreshmentPointDetailsController detailsController) {
        this.detailsController = detailsController;
    }

    /**
     * Shows search results (called from previous use case).
     */
    public void showSearchResults(List<RefreshmentPointDetailsDTO> results) {
        // In a full application, this would pass results to the appropriate UI.
        System.out.println("Coordinator: showing search results.");
    }

    /**
     * Shows details for a selected refreshment point.
     * Called when user selects an item from search results.
     */
    public void showDetails(Long selectedId) {
        System.out.println("Coordinator: showing details for ID " + selectedId);
        RefreshmentPointDetailsDTO dto = detailsController.viewDetails(selectedId);
        // In a real UI, this would pass the DTO to a details view.
        System.out.println("Details retrieved: " + dto.getDetails());
    }

    /**
     * Called by SearchResultsUI when user selects an item.
     */
    public void notifySelection(Long selectedId) {
        showDetails(selectedId);
    }
}