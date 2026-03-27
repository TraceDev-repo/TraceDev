package com.agency.reporting.auth;

import com.agency.reporting.domain.AgencyOperator;

/**
 * Represents the current user session with authentication state.
 */
public class UserSession {
    private AgencyOperator operator;
    private boolean authenticated;

    public UserSession() {
        this.authenticated = false;
    }

    public AgencyOperator getOperator() {
        return operator;
    }

    public void setOperator(AgencyOperator operator) {
        this.operator = operator;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean login(Object credentials) {
        // Simplified login - actual authentication logic would be implemented here
        // For demonstration, assume credentials are valid and set a dummy operator
        this.operator = new AgencyOperator("op1", "Operator One", "agency1");
        this.authenticated = true;
        return true;
    }

    public void logout() {
        this.operator = null;
        this.authenticated = false;
    }
}