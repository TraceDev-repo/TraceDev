package com.example;

import com.example.ui.NewsManagementUI;

/**
 * Main application class to simulate the Agency Operator activating the delete function.
 * This makes the code runnable as per requirements.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Simulate Agency Operator
        NewsManagementUI ui = new NewsManagementUI();
        ui.activateDeleteNewsFunction();
    }
}