package com.newsagency.service;

import com.newsagency.model.SessionManager;

/**
 * Application Service for authentication and authorization.
 * Implements REQ-001 (Authentication).
 */
public class AuthService {

    private SessionManager sessionManager;

    public AuthService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Checks if the current session is authenticated.
     * This method is called at the start of the sequence diagrams.
     */
    public boolean checkAuthentication() {
        return validateSession();
    }

    /**
     * Validates the current session.
     * In a real implementation, this would check session expiry and integrity.
     */
    public boolean validateSession() {
        return sessionManager != null && sessionManager.isValid();
    }

    /**
     * Gets the role of a user.
     */
    public String getUserRole(String userId) {
        // Simplified implementation: assume all authenticated users are "OPERATOR"
        if (isUserAuthenticated(userId)) {
            return "OPERATOR";
        }
        return "GUEST";
    }

    /**
     * Checks if a specific user is authenticated.
     */
    public boolean isUserAuthenticated(String userId) {
        // Check if session is valid and belongs to the given user
        return validateSession() &&
               sessionManager.getUserId() != null &&
               sessionManager.getUserId().equals(userId);
    }

    /**
     * Displays a login required message (used in alternative flow).
     */
    public void displayLoginRequired() {
        System.out.println("Please login to continue.");
    }
}