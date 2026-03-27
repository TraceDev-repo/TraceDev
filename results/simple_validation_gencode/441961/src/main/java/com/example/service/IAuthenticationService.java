package com.example.service;

/**
 * Service interface for verifying Agency Operator login state.
 */
public interface IAuthenticationService {
    /**
     * Verifies whether the session belongs to a logged-in Agency Operator.
     * @param sessionId the current session identifier
     * @return true if the session is valid for an Agency Operator, false otherwise
     */
    boolean verifyAgencyOperatorSession(String sessionId);
}