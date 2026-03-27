package com.example.domain;

/**
 * Actor initiating search. Must be authenticated via credentials validation.
 */
public class User {
    private final String id;
    private final String username;
    private boolean isAuthenticated;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.isAuthenticated = false;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * Authenticates the user with given credentials.
     * In a real implementation, this would involve checking against a store.
     * For simplicity, returns true if credentials are not null.
     */
    public boolean authenticate(Credentials credentials) {
        if (credentials != null && credentials.isValid()) {
            isAuthenticated = true;
            return true;
        }
        isAuthenticated = false;
        return false;
    }
}