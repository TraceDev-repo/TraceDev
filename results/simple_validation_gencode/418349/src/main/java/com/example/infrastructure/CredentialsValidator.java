package com.example.infrastructure;

import com.example.domain.Credentials;

/**
 * Infrastructure component for validating credentials.
 */
public class CredentialsValidator {
    /**
     * Validates credentials.
     * For demo, accepts a fixed username/password.
     */
    public boolean validate(Credentials credentials) {
        if (credentials == null || !credentials.isValid()) {
            return false;
        }
        // Simple hard‑coded check for demonstration.
        return "demo".equals(credentials.getUsername()) &&
               "demo123".equals(credentials.getPassword());
    }

    /**
     * Checks password strength (simple rule: at least 6 characters).
     */
    public boolean checkPasswordStrength(String password) {
        return password != null && password.length() >= 6;
    }
}