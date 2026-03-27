package com.etour.agency;

/**
 * Represents an agency operator who can log in and out.
 * Interacts with the system to edit refreshment points.
 */
public class AgencyOperator {
    private String username;
    private String password;

    public AgencyOperator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Attempts to log in the operator.
     * @return true if login is successful, false otherwise.
     */
    public boolean login() {
        // In a real implementation, this would validate credentials against a database.
        System.out.println("Operator " + username + " logged in.");
        return true;
    }

    /**
     * Logs out the operator.
     */
    public void logout() {
        System.out.println("Operator " + username + " logged out.");
    }
}