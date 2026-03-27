package com.example.service;

/**
 * Service responsible for user authentication.
 */
public class AuthenticationService {
    
    /**
     * Verifies if the user with the given ID is logged in.
     * @param userId the user ID to check
     * @return true if user is logged in, false otherwise
     */
    public boolean verifyUserLoggedIn(String userId) {
        // In a real system, this would check session, tokens, etc.
        // For demonstration, assume user is logged in if userId is not null/empty
        return userId != null && !userId.trim().isEmpty();
    }
}