package com.example;

import com.example.ui.AccountStatusController;
import com.example.application.UpdateTouristAccountStatusService;
import com.example.application.IUpdateTouristAccountStatusUseCase;
import com.example.domain.AccountStatus;
import com.example.infrastructure.*;
import com.example.domain.AgencyOperator;

/**
 * Main application class demonstrating the complete flow.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("=== Tourist Account Status Update Demo ===");

        // Setup infrastructure
        // Create and connect to ETOUR server (simulated)
        ETOURServerConnection connection = new ETOURServerConnection();
        connection.connect();
        
        TouristAccountRepositoryImpl repository = new TouristAccountRepositoryImpl(connection);
        NotificationServiceImpl notificationService = new NotificationServiceImpl(connection);
        
        // PerformanceMonitor is created here and passed to services as per sequence diagram
        PerformanceMonitor performanceMonitor = new PerformanceMonitor(2);
        performanceMonitor.start();
        
        ConfirmationServiceImpl confirmationService = new ConfirmationServiceImpl(connection, performanceMonitor);
        
        // Set performance monitors
        repository.setPerformanceMonitor(performanceMonitor);
        notificationService.setPerformanceMonitor(performanceMonitor);

        // Create use case service with required PerformanceMonitor dependency
        UpdateTouristAccountStatusService useCaseService = new UpdateTouristAccountStatusService(
                repository, notificationService, confirmationService, performanceMonitor);

        // Setup authentication and authorization (simulated)
        IAuthenticationService authService = new IAuthenticationService() {
            @Override
            public AgencyOperator getCurrentOperator() {
                return new AgencyOperator("OP001", "Alice Operator", "AGENCY_OPERATOR");
            }

            @Override
            public boolean isAgencyOperatorLoggedIn() {
                return true; // Simulate logged in
            }
        };

        IAuthorizationService authzService = new IAuthorizationService() {
            @Override
            public boolean hasPermission(AgencyOperator operator, String permission) {
                return "UPDATE_ACCOUNT_STATUS".equals(permission);
            }
        };

        // Create controller
        AccountStatusController controller = new AccountStatusController(
                useCaseService, authService, authzService);

        // Execute status change
        try {
            controller.changeStatus("TA001", AccountStatus.INACTIVE);
            System.out.println("Demo completed successfully.");
        } catch (AccountStatusController.TimeoutException e) {
            System.err.println("TimeoutException propagated to UI: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Demo failed with error: " + e.getMessage());
        } finally {
            connection.disconnect();
        }
    }
}