package com.agency;

/**
 * Represents a tourist account.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String email;
    private boolean isActive;

    public Tourist(String id, String name, String email) {
        this.touristId = id;
        this.name = name;
        this.email = email;
        this.isActive = true; // By default, a newly created tourist is active.
    }

    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Deactivates the tourist account.
     */
    public void deactivate() {
        this.isActive = false;
        System.out.println("Tourist " + touristId + " deactivated.");
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}