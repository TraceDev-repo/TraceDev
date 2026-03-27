package com.example.ui;

import com.example.controller.DeleteNewsController;
import com.example.model.News;
import com.example.exception.ETourConnectionException;
import java.util.List;
import java.util.Scanner;

/**
 * Boundary/UI form for news deletion.
 * Implements interactions as per sequence diagram.
 */
public class NewsDeletionForm {
    private DeleteNewsController controller;
    private Scanner scanner;
    private String selectedNewsId;

    public NewsDeletionForm(DeleteNewsController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Creates and shows the form (simulated via console).
     * Called by NewsManagementUI as per sequence diagram.
     */
    public void createAndShow() {
        System.out.println("=== News Deletion Form ===");
        try {
            List<News> newsList = controller.displayAllNews();
            displayNewsListInForm(newsList);
        } catch (ETourConnectionException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Displays the list of news fetched from controller.
     * As per sequence diagram: controller returns list, form displays it.
     * @param newsList the list of news to display
     */
    public void displayNewsListInForm(List<News> newsList) {
        if (newsList.isEmpty()) {
            System.out.println("No news available.");
            return;
        }
        System.out.println("Available News:");
        for (News news : newsList) {
            System.out.println(news);
        }
        handleNewsSelection();
    }

    /**
     * Handles news selection by the operator.
     * As per sequence diagram: AO selects a news from the list.
     */
    private void handleNewsSelection() {
        System.out.print("Enter the ID of the news to delete: ");
        selectedNewsId = scanner.nextLine().trim();
        submitForDeletion();
    }

    /**
     * Submits the selected news for deletion.
     * As per sequence diagram: AO triggers submission.
     */
    private void submitForDeletion() {
        // Step 5-6: Confirmation Flow
        boolean confirmed = requestConfirmation();
        if (!confirmed) {
            notifyCancellation();
            return;
        }
        // Step 7: Execute Deletion
        try {
            controller.executeDeletion(selectedNewsId);
            notifySuccess();
        } catch (ETourConnectionException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Requests confirmation from the user.
     * As per sequence diagram: Form -> Controller: requestConfirmation(selectedNewsId)
     * Implement actual user interaction.
     * @return true if confirmed, false if cancelled
     */
    private boolean requestConfirmation() {
        System.out.print("Are you sure you want to delete news with ID " + selectedNewsId + "? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (!response.equals("yes")) {
            return false;
        }
        // Also call controller's confirmation logic
        return controller.requestConfirmation(selectedNewsId);
    }

    /**
     * Notifies successful deletion.
     * As per sequence diagram: Exit Condition - Successfully eliminated.
     */
    private void notifySuccess() {
        System.out.println("Success: News deleted successfully.");
    }

    /**
     * Notifies cancellation of operation.
     * As per sequence diagram: Operator cancels operation.
     */
    private void notifyCancellation() {
        System.out.println("Operation cancelled by operator.");
    }

    /**
     * Displays an error message.
     * As per sequence diagram: used when connection is interrupted.
     * @param message the error message
     */
    private void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}