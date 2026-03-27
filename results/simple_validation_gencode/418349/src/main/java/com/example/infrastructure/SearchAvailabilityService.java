package com.example.infrastructure;

import java.util.Date;

/**
 * Manages search functionality availability independently from authentication.
 */
public class SearchAvailabilityService {
    private boolean isSearchAvailable = true;
    private Date lastChecked = new Date();

    public boolean isSearchAvailable() {
        return isSearchAvailable;
    }

    /**
     * Validates that search is currently available.
     * In a real implementation, might check system load, maintenance windows, etc.
     */
    public boolean validateSearchAvailability() {
        lastChecked = new Date();
        // Simulate a check: for demo, always true.
        isSearchAvailable = true;
        return isSearchAvailable;
    }

    public void updateAvailabilityStatus(boolean status) {
        this.isSearchAvailable = status;
        this.lastChecked = new Date();
    }

    public Date getLastCheckedTime() {
        return lastChecked;
    }
}