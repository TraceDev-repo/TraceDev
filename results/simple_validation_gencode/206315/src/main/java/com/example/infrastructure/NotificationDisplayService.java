package com.example.infrastructure;

/**
 * Service for displaying notifications to the user.
 */
public interface NotificationDisplayService {
    void displayNotification(String notificationId, String message);
}