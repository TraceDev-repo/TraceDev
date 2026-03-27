package com.example;

import com.example.agency.AgencyOperator;
import com.example.view.RefreshmentPointListView;
import com.example.view.ConfirmationDialog;
import com.example.service.DeleteRefreshmentPointService;
import com.example.service.AuthenticationService;
import com.example.service.SearchCulturalHeritageService;
import com.example.repository.RefreshmentPointRepositoryImpl;
import com.example.utility.ServerConnectionManager;
import com.example.utility.SystemNotification;
import com.example.refreshmentpoint.RefreshmentPoint;

/**
 * Main class to demonstrate the system flow as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Delete Refreshment Point Use Case ===\n");
        
        // 1. Setup components (as per dependency injection)
        RefreshmentPointRepositoryImpl repository = new RefreshmentPointRepositoryImpl();
        AuthenticationService authService = new AuthenticationService();
        ServerConnectionManager connectionManager = new ServerConnectionManager();
        SystemNotification notification = new SystemNotification();
        DeleteRefreshmentPointService service = new DeleteRefreshmentPointService(
                repository, authService, connectionManager, notification);
        ConfirmationDialog dialog = new ConfirmationDialog();
        SearchCulturalHeritageService searchService = new SearchCulturalHeritageService();
        RefreshmentPointListView view = new RefreshmentPointListView(service, dialog, searchService);
        
        // 2. Create agency operator (actor)
        AgencyOperator operator = new AgencyOperator("operator123", "John Doe", true);
        
        // 3. Simulate continuation from SearchCulturalHeritage use case
        System.out.println("1. Continuation from SearchCulturalHeritage use case:");
        view.loadFromSearch();
        
        // 4. Agency operator selects a refreshment point
        System.out.println("\n2. Agency operator selects a refreshment point:");
        RefreshmentPoint selected = view.selectPoint("RP1");
        if (selected == null) {
            System.out.println("Point not found, exiting.");
            return;
        }
        
        // 5. Display point details
        System.out.println("\n3. Displaying point details:");
        System.out.println("   Name: " + selected.getName());
        System.out.println("   Location: " + selected.getLocation());
        
        // 6. Agency operator clicks delete button
        System.out.println("\n4. Agency operator clicks delete button:");
        view.triggerDeleteAction();
        
        // 7. Demonstrate cancellation before confirmation (alternative flow)
        System.out.println("\n--- Alternative flow: Cancellation before confirmation ---");
        view.selectPoint("RP2");
        System.out.println("Agency operator cancels before confirmation:");
        view.triggerDeleteAction();
        
        // 8. Demonstrate connection error flow
        System.out.println("\n--- Alternative flow: Connection error ---");
        connectionManager.setConnected(false);
        view.selectPoint("RP1");
        view.triggerDeleteAction();
        
        // 9. Demonstrate authentication failure (needs no real setup as authentication service returns true by default)
        System.out.println("\n=== End of demonstration ===");
    }
}