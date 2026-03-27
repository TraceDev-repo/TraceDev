package com.example.interfaces.dto;

/**
 * DTO for notification confirmation.
 */
public class NotificationConfirmationDTO {
    private String notificationId;
    private boolean confirmed;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}