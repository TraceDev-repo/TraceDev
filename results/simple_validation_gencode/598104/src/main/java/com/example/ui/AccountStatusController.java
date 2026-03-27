package com.example.ui;

import com.example.application.IUpdateTouristAccountStatusUseCase;
import com.example.application.UpdateTouristAccountStatusCommand;
import com.example.domain.AccountStatus;
import com.example.domain.AgencyOperator;
import com.example.infrastructure.IAuthenticationService;
import com.example.infrastructure.IAuthorizationService;
import com.example.infrastructure.PerformanceMonitor;

/**
 * Controller for handling account status change requests from the UI.
 * Validates entry conditions (agency operator logged in with permission).
 */
public class AccountStatusController {

    private IUpdateTouristAccountStatusUseCase useCase;
    private IAuthenticationService authenticationService;
    private IAuthorizationService authorizationService;

    public AccountStatusController(IUpdateTouristAccountStatusUseCase useCase,
                                   IAuthenticationService authenticationService,
                                   IAuthorizationService authorizationService) {
        this.useCase = useCase;
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
    }

    public void changeStatus(String touristAccountId, AccountStatus newStatus) {
        // Entry Conditions: Agency Operator IS logged in
        if (!authenticationService.isAgencyOperatorLoggedIn()) {
            throw new SecurityException("Agency operator is not logged in.");
        }

        AgencyOperator operator = authenticationService.getCurrentOperator();
        if (!authorizationService.hasPermission(operator, "UPDATE_ACCOUNT_STATUS")) {
            throw new SecurityException("Operator lacks permission to update account status.");
        }

        // Initialize Performance Monitor with 2-second timeout (REQ-QUAL-01)
        PerformanceMonitor performanceMonitor = new PerformanceMonitor(2);
        performanceMonitor.start();

        // Inject monitor into service (assume service has a setter)
        if (useCase instanceof com.example.application.UpdateTouristAccountStatusService) {
            // PerformanceMonitor is now passed in constructor, so we need to create a new service instance
            // Since we cannot modify existing instance, we'll handle timeout exception at controller level
            // The performance checks will be handled by the service's performance monitor
        }

        // Create command and execute use case
        UpdateTouristAccountStatusCommand command = new UpdateTouristAccountStatusCommand(touristAccountId, newStatus);
        try {
            useCase.execute(command);
            System.out.println("Status change successful for account: " + touristAccountId);
        } catch (Exception e) {
            System.err.println("Error during status change: " + e.getMessage());
            // Handle specific exceptions (timeout, connection, etc.) as per sequence diagram
            if (e instanceof PerformanceMonitor.TimeoutException) {
                System.err.println("Timeout error: Operation took too long.");
                // Rethrow with specific type as per design
                throw new TimeoutException("Operation exceeded timeout limit.", e);
            } else if (e instanceof com.example.infrastructure.TouristAccountRepositoryImpl.ConnectionLostException) {
                System.err.println("Connection error: Lost connection to ETOUR server.");
                throw e;
            } else if (e instanceof RuntimeException && e.getMessage().contains("Operation cancelled")) {
                System.err.println("Operation was cancelled by user.");
                throw e;
            }
            throw e;
        }
    }
    
    public static class TimeoutException extends RuntimeException {
        public TimeoutException(String message) {
            super(message);
        }
        public TimeoutException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}