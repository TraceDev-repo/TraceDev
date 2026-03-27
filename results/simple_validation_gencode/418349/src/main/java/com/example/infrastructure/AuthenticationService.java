package com.example.infrastructure;

import com.example.domain.Credentials;
import com.example.domain.User;

/**
 * Validates user authentication with CredentialsValidator.
 */
public class AuthenticationService {
    private User currentUser;
    private final CredentialsValidator credentialsValidator;

    public AuthenticationService(CredentialsValidator credentialsValidator) {
        this.credentialsValidator = credentialsValidator;
        // For simplicity, start with no user.
        this.currentUser = null;
    }

    public boolean isUserAuthenticated() {
        return currentUser != null && currentUser.isAuthenticated();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Authenticates a user with credentials.
     * Returns the authenticated user if successful, null otherwise.
     */
    public User authenticateUser(Credentials credentials) {
        if (credentialsValidator.validate(credentials)) {
            // Simulate user creation/retrieval
            currentUser = new User("user-id-1", credentials.getUsername());
            currentUser.setAuthenticated(true);
            return currentUser;
        }
        return null;
    }

    public void logoutUser() {
        if (currentUser != null) {
            currentUser.setAuthenticated(false);
        }
        currentUser = null;
    }
}