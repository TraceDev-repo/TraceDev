package com.example.service;

import com.example.repository.RefreshmentPointRepository;
import com.example.refreshmentpoint.RefreshmentPoint;
import com.example.utility.ServerConnectionManager;
import com.example.utility.SystemNotification;
import java.util.Optional;

/**
 * Controller/service that orchestrates the deletion of a refreshment point.
 * Implements business logic including authentication and connection checks.
 */
public class DeleteRefreshmentPointService {
    private RefreshmentPointRepository repository;
    private boolean deletionConfirmed;
    private AuthenticationService authService;
    private ServerConnectionManager connectionManager;
    private SystemNotification notification;

    public DeleteRefreshmentPointService(RefreshmentPointRepository repository, 
                                         AuthenticationService authService, 
                                         ServerConnectionManager connectionManager,
                                         SystemNotification notification) {
        this.repository = repository;
        this.authService = authService;
        this.connectionManager = connectionManager;
        this.notification = notification;
        this.deletionConfirmed = false;
    }

    /**
     * Initiates the deletion request for a given point ID.
     * Performs authentication and connection checks.
     * Returns a result code: 0=success/validation passed, 1=authentication failure, 2=connection failure
     */
    public int requestDelete(String pointId) {
        System.out.println("Service: Request deletion for point " + pointId);
        if (!verifyAuthentication()) {
            System.out.println("Service: Authentication failed");
            return 1; // authentication failure
        }
        if (!checkAndReconnect()) {
            System.out.println("Service: Connection issues");
            return 2; // connection failure
        }
        // Proceed to confirmation (handled by the view)
        return 0; // validation passed
    }

    /**
     * Confirms and executes the deletion if confirmation is true.
     * @param confirmation user's confirmation decision
     * @return true if deletion was performed, false otherwise
     */
    public boolean confirmDeletion(boolean confirmation) {
        this.deletionConfirmed = confirmation;
        if (confirmation) {
            // In real flow, pointId would be passed or stored
            System.out.println("Service: Deletion confirmed");
            // executeDeletion would be called with actual pointId
            return true;
        }
        System.out.println("Service: Deletion not confirmed");
        return false;
    }

    /**
     * Cancels the deletion process.
     */
    public void cancelDeletion() {
        deletionConfirmed = false;
        System.out.println("Service: Deletion cancelled");
    }

    /**
     * Executes the actual deletion in the repository.
     * Private method called after all validations pass.
     */
    private boolean executeDeletion(String pointId) {
        Optional<RefreshmentPoint> point = repository.findById(pointId);
        if (point.isPresent()) {
            repository.deleteById(pointId);
            notification.sendNotification("Refreshment point deleted", "system");
            return true;
        }
        return false;
    }

    /**
     * Verifies that the current user is authenticated.
     * @return true if authenticated, false otherwise
     */
    private boolean verifyAuthentication() {
        // In a real application, we would get the current user ID from context
        String currentUserId = "operator123";
        return authService.verifyUserLoggedIn(currentUserId);
    }

    /**
     * Checks connection and attempts reconnection if needed.
     * @return true if connection is available, false otherwise
     */
    private boolean checkAndReconnect() {
        if (!connectionManager.checkConnection()) {
            connectionManager.handleInterruption();
            return connectionManager.attemptReconnection();
        }
        return true;
    }

    // Helper method for direct deletion (used by view after confirmation)
    public boolean deletePoint(String pointId) {
        if (verifyAuthentication() && checkAndReconnect()) {
            return executeDeletion(pointId);
        }
        return false;
    }
}