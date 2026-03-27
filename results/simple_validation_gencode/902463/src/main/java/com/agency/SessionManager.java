package com.agency;

/**
 * Manages user sessions.
 * This is a helper class for AuthenticationService.
 */
public class SessionManager {
    /**
     * Checks if the operator has a valid session.
     * @param operator the operator to check.
     * @return true if session is valid, false otherwise.
     */
    public boolean isValidSession(AgencyOperator operator) {
        // Simplified: always return true for demonstration.
        // In a real system, this would check session tokens or timestamps.
        return true;
    }
}