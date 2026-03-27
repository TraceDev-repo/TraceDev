package com.example.presentation;

import com.example.application.DeleteCulturalHeritageController;
import com.example.domain.dto.CulturalHeritageDTO;
import java.util.List;
import java.util.Scanner;

/**
 * View responsible for displaying the UI for deleting cultural heritages.
 * It interacts with the controller to handle user actions.
 */
public class DeleteCulturalHeritageView {
    private DeleteCulturalHeritageController controller;
    private Scanner scanner;

    public DeleteCulturalHeritageView(DeleteCulturalHeritageController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void setController(DeleteCulturalHeritageController controller) {
        this.controller = controller;
    }

    /**
     * Displays the list of cultural heritages.
     * @param heritages List of CulturalHeritageDTO objects to display.
     */
    public void displayList(List<CulturalHeritageDTO> heritages) {
        System.out.println("=== Cultural Heritage List ===");
        if (heritages.isEmpty()) {
            System.out.println("No cultural heritages found.");
        } else {
            for (CulturalHeritageDTO heritage : heritages) {
                System.out.println("ID: " + heritage.getId() + ", Name: " + heritage.getName() + ", Description: " + heritage.getDescription());
            }
        }
        System.out.println("=============================");
    }

    /**
     * Asks the user for confirmation before deleting a cultural heritage.
     * @param heritage The CulturalHeritageDTO to be deleted.
     */
    public void askForConfirmation(CulturalHeritageDTO heritage) {
        System.out.println("Selected Heritage: " + heritage.getName() + " (" + heritage.getId() + ")");
        System.out.print("Are you sure you want to delete this cultural heritage? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            controller.handleConfirmationConfirmed();
        } else {
            controller.handleOperationCancelled();
        }
    }

    /**
     * Shows a success message to the user.
     * @param message The success message.
     */
    public void showSuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * Shows an error message to the user.
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * Blocks input controls (simulated by a message).
     */
    public void blockInputControls() {
        System.out.println("[UI] Input controls blocked.");
    }

    /**
     * Unblocks input controls (simulated by a message).
     */
    public void unblockInputControls() {
        System.out.println("[UI] Input controls unblocked.");
    }

    /**
     * Simulates the view being loaded.
     */
    public void simulateViewLoaded() {
        controller.handleViewLoaded();
    }

    /**
     * Simulates user selecting a heritage.
     */
    public void simulateHeritageSelection() {
        System.out.print("Enter the ID of the cultural heritage to delete: ");
        String heritageId = scanner.nextLine().trim();
        controller.handleHeritageSelected(heritageId);
    }
}