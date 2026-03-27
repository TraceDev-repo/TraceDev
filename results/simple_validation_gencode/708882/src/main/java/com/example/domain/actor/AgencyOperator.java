package com.example.domain.actor;

/**
 * Represents an Agency Operator actor in the system.
 * Handles authentication state for operators.
 */
public class AgencyOperator {
    private String id;
    private String username;
    private boolean isAuthenticated;

    /**
     * Constructor.
     * @param id the operator's unique identifier
     * @param username the operator's username
     */
    public AgencyOperator(String id, String username) {
        this.id = id;
        this.username = username;
        this.isAuthenticated = false;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Authenticates the operator.
     */
    public void authenticate() {
        this.isAuthenticated = true;
    }

    /**
     * Logs out the operator.
     */
    public void logout() {
        this.isAuthenticated = false;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}