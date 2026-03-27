package com.example;

/**
 * Service for authentication and user context.
 */
public interface AuthenticationService {
    /**
     * Checks if a user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    boolean isUserLoggedIn();

    /**
     * Gets the current logged-in user.
     * @return the user object.
     * @throws SecurityException if no user is logged in.
     */
    User getCurrentUser();
}