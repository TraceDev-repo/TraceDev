package com.example;

import com.example.controller.UseCaseController;
import com.example.repository.ConventionRepositoryImpl;
import com.example.repository.IConventionRepository;
import com.example.dao.AgencyDAO;
import com.example.dao.FormDAO;
import com.example.view.HistoryDisplayView;
import com.example.server.ETourServer;
import com.example.data.ConventionData;
import com.example.domain.AgencyOperator;

// Main application class to demonstrate the system
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Convention History System...\n");
        
        // Initialize components
        ETourServer eTourServer = new ETourServer();
        IConventionRepository repository = new ConventionRepositoryImpl(eTourServer);
        AgencyDAO agencyDAO = new AgencyDAO();
        FormDAO formDAO = new FormDAO();
        HistoryDisplayView view = new HistoryDisplayView();
        UseCaseController controller = new UseCaseController(repository, agencyDAO, formDAO, view);
        AgencyOperator operator = new AgencyOperator();
        
        // Scenario 1: Successful flow
        System.out.println("=== Scenario 1: Successful Flow ===");
        simulateSuccessfulFlow(controller, view);
        
        System.out.println("\n=== Scenario 2: Preconditions Not Met ===");
        simulateFailedPreconditions(controller, view);
        
        System.out.println("\n=== Scenario 3: Server Connection Lost ===");
        simulateConnectionLoss(controller, view, eTourServer);
        
        System.out.println("\nSystem demonstration complete.");
    }
    
    private static void simulateSuccessfulFlow(UseCaseController controller, HistoryDisplayView view) {
        // Agency Operator accesses the feature
        System.out.println("Agency Operator accesses the feature...");
        
        // System prompts for data upload
        ConventionData data = view.promptForConventionData();
        
        // Controller processes upload and displays history
        controller.uploadConventionData(data);
    }
    
    private static void simulateFailedPreconditions(UseCaseController controller, HistoryDisplayView view) {
        // Create data for a non-existent or invalid agency
        ConventionData invalidData = new ConventionData();
        invalidData.setAgencyId(999); // Non-existent agency
        invalidData.setSelectedRestaurantId(100);
        invalidData.setUploadedData("Invalid data");
        
        // Try to upload - should fail precondition validation
        controller.uploadConventionData(invalidData);
    }
    
    private static void simulateConnectionLoss(UseCaseController controller, HistoryDisplayView view, ETourServer server) {
        // Simulate server connection interruption
        server.simulateConnectionInterruption();
        
        // Create valid data
        ConventionData data = new ConventionData();
        data.setAgencyId(1);
        data.setSelectedRestaurantId(100);
        data.setUploadedData("Test data");
        
        // Try to upload - should fail due to connection loss
        controller.uploadConventionData(data);
        
        // Restore connection for other tests
        server.restoreConnection();
    }
}