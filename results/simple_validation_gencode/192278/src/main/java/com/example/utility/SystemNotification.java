package com.example.utility;

/**
 * Utility class for sending system notifications.
 */
public class SystemNotification {
    
    /**
     * Sends a notification to a specific recipient.
     * @param message the notification message
     * @param recipient the recipient identifier
     */
    public void sendNotification(String message, String recipient) {
        System.out.println("Notification sent to " + recipient + ": " + message);
    }

    /**
     * Broadcasts a notification to all relevant parties.
     * @param message the notification message
     */
    public void broadcastNotification(String message) {
        System.out.println("Broadcast notification: " + message);
    }
}