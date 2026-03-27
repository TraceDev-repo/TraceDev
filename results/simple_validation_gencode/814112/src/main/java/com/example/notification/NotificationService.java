package com.example.notification;

import com.example.news.NotificationType;

/**
 * Service for sending notifications to the user.
 */
public class NotificationService {

    /**
     * Sends a notification (e.g., to UI, log, or external system).
     * @param message the message.
     * @param type the notification type.
     */
    public void sendNotification(String message, NotificationType type) {
        showDialog(message, type);
        logNotification(message, type);
    }

    /**
     * Shows a dialog (in a real UI implementation).
     * @param message the message.
     * @param type the type.
     */
    public void showDialog(String message, NotificationType type) {
        System.out.println("NotificationService [" + type + "]: " + message);
    }

    /**
     * Logs the notification.
     * @param message the message.
     * @param type the type.
     */
    public void logNotification(String message, NotificationType type) {
        // In a real system, this would write to a log file or system.
        // For simplicity, we just print to console.
        System.out.println("Logged notification: [" + type + "] " + message);
    }
}