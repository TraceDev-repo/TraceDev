package com.example.service;

/**
 * Concrete authentication service that validates user sessions.
 * Assumption: In a real scenario, it would integrate with a session store or identity provider.
 */
public class AuthenticationService implements IAuthenticationService {

    @Override
    public boolean verifyAgencyOperatorSession(String sessionId) {
        // For demonstration, assume sessionId is non-null and not empty.
        // In reality, we would check against a session store or token validation.
        return sessionId != null && !sessionId.trim().isEmpty();
    }
}