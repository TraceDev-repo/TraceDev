package com.example.view;

import com.example.service.DeleteRefreshmentPointService;
import com.example.service.SearchCulturalHeritageService;
import com.example.refreshmentpoint.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Boundary class representing the UI view for refreshment point list and deletion.
 */
public class RefreshmentPointListView {
    private DeleteRefreshmentPointService service;
    private RefreshmentPoint selectedPoint;
    private ConfirmationDialog confirmationDialog;
    private SearchCulturalHeritageService searchService;

    public RefreshmentPointListView(DeleteRefreshmentPointService service, 
                                    ConfirmationDialog confirmationDialog,
                                    SearchCulturalHeritageService searchService) {
        this.service = service;
        this.confirmationDialog = confirmationDialog;
        this.searchService = searchService;
    }

    /**
     * Displays the list of refreshment points.
     */
    public void displayList(List<RefreshmentPoint> points) {
        System.out.println("=== Refreshment Points List ===");
        for (RefreshmentPoint point : points) {
            System.out.println("ID: " + point.getPointId() + 
                             ", Name: " + point.getName() + 
                             ", Location: " + point.getLocation());
        }
        System.out.println("===============================");
    }

    /**
     * Selects a point by ID from the current search results.
     * @param pointId the ID of the point to select
     * @return the selected point, or null if not found
     */
    public RefreshmentPoint selectPoint(String pointId) {
        List<RefreshmentPoint> points = searchService.getSearchResults();
        Optional<RefreshmentPoint> found = points.stream()
                .filter(p -> p.getPointId().equals(pointId))
                .findFirst();
        if (found.isPresent()) {
            selectedPoint = found.get();
            System.out.println("View: Selected point: " + selectedPoint.getName());
            return selectedPoint;
        }
        System.out.println("View: Point with ID " + pointId + " not found");
        return null;
    }

    /**
     * Shows a confirmation dialog and returns user's decision.
     * @return true if user confirms, false otherwise
     */
    public boolean getDeleteConfirmation() {
        return confirmationDialog.showConfirmation("Delete refreshment point " + 
                (selectedPoint != null ? selectedPoint.getName() : "?") + "?");
    }

    /**
     * Triggers the delete action (called when user clicks delete button).
     * Integrates confirmation dialog before calling service.
     */
    public void triggerDeleteAction() {
        if (selectedPoint != null) {
            // System asks for confirmation before deletion
            boolean confirmed = getDeleteConfirmation();
            if (confirmed) {
                int result = service.requestDelete(selectedPoint.getPointId());
                if (result == 0) {
                    // Validation passed, execute deletion
                    boolean deletionSuccess = executeDeletion();
                    if (deletionSuccess) {
                        showSuccessMessage();
                    }
                } else if (result == 1) {
                    showAuthenticationError();
                } else if (result == 2) {
                    showConnectionError();
                }
            } else {
                service.cancelDeletion();
                showCancellationMessage();
            }
        } else {
            System.out.println("View: No point selected for deletion");
        }
    }

    /**
     * Loads refreshment points from search service (simulates continuation from SearchCulturalHeritage use case).
     */
    public void loadFromSearch() {
        List<RefreshmentPoint> points = searchService.getSearchResults();
        displayList(points);
    }

    /**
     * Executes the deletion after confirmation.
     * @return true if deletion succeeded, false otherwise
     */
    public boolean executeDeletion() {
        if (selectedPoint == null) {
            return false;
        }
        boolean success = service.deletePoint(selectedPoint.getPointId());
        return success;
    }

    // --- Response methods called by service or dialog ---
    
    public void showSuccessMessage() {
        System.out.println("View: Refreshment point deleted successfully!");
    }

    public void showCancellationMessage() {
        System.out.println("View: Deletion was cancelled.");
    }

    public void showConnectionError() {
        System.out.println("View: Connection error. Please check your network.");
    }

    public void showAuthenticationError() {
        System.out.println("View: Authentication error. Please log in again.");
    }

    public void showErrorNotification(String message) {
        System.out.println("View: Error - " + message);
    }

    public RefreshmentPoint getSelectedPoint() {
        return selectedPoint;
    }
}