package com.agency;

/**
 * Concrete implementation of IAuthenticationService.
 * Uses a SessionManager for session validation.
 */
public class AuthenticationService implements IAuthenticationService {
    private SessionManager sessionManager;

    public AuthenticationService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean validateCredentials(AgencyOperator operator) {
        // In a real scenario, this would validate against a database or identity provider.
        // For simplicity, we assume credentials are valid if operator ID is not empty.
        return operator.getOperatorId() != null && !operator.getOperatorId().isEmpty();
    }

    @Override
    public boolean isSessionValid(AgencyOperator operator) {
        // Delegate to session manager to check session validity.
        return sessionManager.isValidSession(operator);
    }
}