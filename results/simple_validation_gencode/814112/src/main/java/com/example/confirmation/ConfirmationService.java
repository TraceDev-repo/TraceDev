package com.example.confirmation;

/**
 * Service for requesting user confirmation.
 */
public class ConfirmationService {

    /**
     * Requests confirmation from the user.
     * @param message the confirmation message.
     * @return true if the user confirms.
     */
    public boolean requestConfirmation(String message) {
        return showConfirmationDialog("Confirm", message);
    }

    /**
     * Shows a confirmation dialog (simulated for console).
     * In a real UI, this would display a modal dialog.
     * @param title dialog title.
     * @param message dialog message.
     * @return true if confirmed.
     */
    public boolean showConfirmationDialog(String title, String message) {
        // Simulating user confirmation for demonstration.
        // In a real application, this would block until the user responds.
        System.out.println("ConfirmationService: " + title + " - " + message);
        System.out.println("Simulating user confirmation (returning true).");
        return true;
    }
}