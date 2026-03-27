package com.example.application.dtos;

/**
 * Result of an activation operation.
 */
public class ActivationResult {
    private boolean success;
    private String message;
    private boolean notificationSent;

    public ActivationResult(boolean success, String message, boolean notificationSent) {
        this.success = success;
        this.message = message;
        this.notificationSent = notificationSent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
}