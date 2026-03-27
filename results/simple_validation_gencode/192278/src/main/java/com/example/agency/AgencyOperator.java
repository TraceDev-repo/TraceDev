package com.example.agency;

/**
 * Represents an agency operator user.
 */
public class AgencyOperator {
    private String userId;
    private String name;
    private boolean loggedIn;

    public AgencyOperator(String userId, String name, boolean loggedIn) {
        this.userId = userId;
        this.name = name;
        this.loggedIn = loggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Requests deletion of a refreshment point.
     * This method delegates to the RefreshmentPointListView.
     */
    public void requestDeleteRefreshmentPoint(String pointId) {
        // In a real implementation, this would trigger the UI flow.
        System.out.println("AgencyOperator: Request deletion for point " + pointId);
    }
}