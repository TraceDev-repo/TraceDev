package com.example.ui;

import com.example.controller.DeleteNewsController;
import com.example.repository.DatabaseNewsRepository;
import javax.sql.DataSource;

/**
 * Boundary/UI layer class representing the main news management UI.
 * As per class diagram: Agency Operator activates the delete function through this UI.
 */
public class NewsManagementUI {
    // Simulated DataSource (in real app would be injected)
    private static final DataSource SIMULATED_DATA_SOURCE = null;

    public void activateDeleteNewsFunction() {
        // Entry Condition: Logged in (assumed)
        System.out.println("News Management UI: Delete function activated.");
        // Create repository and controller as per the dependency flow
        DatabaseNewsRepository repo = new DatabaseNewsRepository(SIMULATED_DATA_SOURCE);
        DeleteNewsController controller = new DeleteNewsController(repo);
        NewsDeletionForm form = new NewsDeletionForm(controller);
        form.createAndShow(); // Simulates showing the form
    }
}