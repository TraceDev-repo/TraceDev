package com.example.adapter.confirmation;

/**
 * Handles user confirmation for operations.
 */
public class ConfirmationHandler {
    private String pendingOperation;
    private String culturalGoodName;

    public ConfirmationHandler(String operation, String culturalGoodName) {
        this.pendingOperation = operation;
        this.culturalGoodName = culturalGoodName;
    }

    public boolean requestConfirmation() {
        // In a real application, this would trigger a UI dialog.
        // For simulation, we assume confirmation is always granted.
        System.out.println("Confirm " + pendingOperation + " for " + culturalGoodName + "?");
        return true;
    }

    public boolean processConfirmation(boolean confirmed) {
        if (confirmed) {
            System.out.println("Operation " + pendingOperation + " confirmed.");
            return true;
        } else {
            System.out.println("Operation cancelled.");
            return false;
        }
    }
}