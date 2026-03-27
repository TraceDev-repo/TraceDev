package com.example.application.services;

import com.example.domain.actor.AgencyOperator;

/**
 * Service for handling operator authentication.
 */
public class AuthenticationService {
    private AgencyOperator currentOperator;

    public AuthenticationService() {
        // Initialize with a default operator for demonstration
        this.currentOperator = new AgencyOperator("op-001", "operator1");
        this.currentOperator.authenticate(); // Auto-login for demo
    }

    /**
     * Logs in an operator.
     * @param username the username
     * @param password the password
     * @return the authenticated operator
     */
    public AgencyOperator login(String username, String password) {
        // Simplified authentication for demonstration
        if ("operator1".equals(username) && "password".equals(password)) {
            currentOperator = new AgencyOperator("op-001", username);
            currentOperator.authenticate();
            return currentOperator;
        }
        throw new IllegalArgumentException("Invalid credentials");
    }

    public AgencyOperator getCurrentOperator() {
        return currentOperator;
    }

    /**
     * Validates that a user is logged in.
     * @return true if authenticated, false otherwise
     */
    public boolean validateLogin() {
        return currentOperator != null && currentOperator.isAuthenticated();
    }

    /**
     * Logs out the current operator.
     */
    public void logout() {
        if (currentOperator != null) {
            currentOperator.logout();
        }
    }
}