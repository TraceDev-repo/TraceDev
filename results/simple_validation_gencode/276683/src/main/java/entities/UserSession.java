package entities;

import java.util.Objects;

/**
 * Represents a UserSession entity added to satisfy authentication requirement.
 * Contains userId, role, and authentication status.
 */
public class UserSession {
    private String userId;
    private String role;
    private boolean isAuthenticated;

    /**
     * Constructor for UserSession.
     * @param userId the user identifier
     * @param role the role of the user (e.g., AGENCY_OPERATOR)
     * @param isAuthenticated whether the user is authenticated
     */
    public UserSession(String userId, String role, boolean isAuthenticated) {
        this.userId = userId;
        this.role = role;
        this.isAuthenticated = isAuthenticated;
    }

    /**
     * Validates authentication as per the class diagram.
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean validateAuthentication() {
        return isAuthenticated;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return isAuthenticated == that.isAuthenticated && Objects.equals(userId, that.userId) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role, isAuthenticated);
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", isAuthenticated=" + isAuthenticated +
                '}';
    }
}