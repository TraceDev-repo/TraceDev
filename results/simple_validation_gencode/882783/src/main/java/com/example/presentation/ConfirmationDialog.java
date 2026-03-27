package com.example.presentation;

/**
 * Dialog for confirming the activation operation.
 */
public class ConfirmationDialog {
    private String message;

    public ConfirmationDialog(String message) {
        this.message = message;
    }

    public boolean show() {
        System.out.println("Showing confirmation dialog: " + message);
        return getResult();
    }

    public boolean getResult() {
        // In a real UI, result would come from user interaction.
        // For simulation, assume user confirms.
        return true;
    }
}