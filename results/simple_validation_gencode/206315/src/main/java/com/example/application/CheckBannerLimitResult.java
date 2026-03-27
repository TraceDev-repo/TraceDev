package com.example.application;

/**
 * Result object for the CheckBannerLimit use case.
 */
public class CheckBannerLimitResult {
    private boolean valid;
    private String message;
    private int currentCount;
    private int maxAllowed;
    private String notificationId;

    public CheckBannerLimitResult(boolean valid, String message, int currentCount, int maxAllowed) {
        this.valid = valid;
        this.message = message;
        this.currentCount = currentCount;
        this.maxAllowed = maxAllowed;
        this.notificationId = null;
    }

    public CheckBannerLimitResult(boolean valid, String message, int currentCount, int maxAllowed, String notificationId) {
        this.valid = valid;
        this.message = message;
        this.currentCount = currentCount;
        this.maxAllowed = maxAllowed;
        this.notificationId = notificationId;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public String getNotificationId() {
        return notificationId;
    }
}