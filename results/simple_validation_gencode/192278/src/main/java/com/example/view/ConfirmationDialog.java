package com.example.view;

/**
 * Boundary class representing a confirmation dialog.
 */
public class ConfirmationDialog {
    
    /**
     * Shows a confirmation dialog with the given message.
     * In a real UI, this would block until user responds.
     * For simulation, we'll assume user confirms.
     * @param message the confirmation message to display
     * @return true if user confirms, false if user cancels
     */
    public boolean showConfirmation(String message) {
        System.out.println("Dialog: " + message + " (Confirm? Y/N)");
        // Simulating user confirmation for demo purposes
        // In real implementation, this would await user input
        boolean simulatedResponse = true; // Assume user confirms
        System.out.println("Dialog: User response: " + (simulatedResponse ? "Confirmed" : "Cancelled"));
        return simulatedResponse;
    }

    /**
     * Cancels the operation.
     */
    public void cancelOperation() {
        System.out.println("Dialog: Operation cancelled by user.");
    }
}