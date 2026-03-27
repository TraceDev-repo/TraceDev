package com.agency.reporting.auth;

/**
 * Service for authentication and session validation.
 */
public class AuthenticationService {
    private UserSession userSession;

    public AuthenticationService(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean validateSession(UserSession session) {
        return session != null && session.isAuthenticated();
    }

    public String getCurrentAgencyId() {
        if (userSession != null && userSession.isAuthenticated() && userSession.getOperator() != null) {
            return userSession.getOperator().getAgencyId();
        }
        return null;
    }
}