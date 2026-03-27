package com.agency.service;

import com.agency.entity.AgencyOperator;
import com.agency.entity.Credentials;

/**
 * Service responsible for operator authentication.
 */
public class AuthenticationService {
    private AgencyOperator currentOperator;

    public AuthenticationResult authenticate(Credentials credentials) {
        // Simplified authentication: assume credentials are valid
        currentOperator = new AgencyOperator("op1", "Operator One");
        currentOperator.login(credentials);
        return new AuthenticationResult(true, "Authentication successful", currentOperator);
    }

    public boolean isAuthenticated(String operatorId) {
        return currentOperator != null && currentOperator.getId().equals(operatorId) && currentOperator.isAuthenticated();
    }

    public void logout(String operatorId) {
        if (currentOperator != null && currentOperator.getId().equals(operatorId)) {
            currentOperator = null;
        }
    }

    public AgencyOperator getCurrentOperator() {
        return currentOperator;
    }
}