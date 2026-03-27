package com.example.infrastructure.notification;

import com.example.application.ports.NotificationService;

/**
 * Adapter for sending email notifications.
 */
public class EmailNotificationService implements NotificationService {
    @Override
    public boolean sendNotification(String message, String recipient) {
        System.out.println("Sending email to " + recipient + ": " + message);
        return true;
    }
}