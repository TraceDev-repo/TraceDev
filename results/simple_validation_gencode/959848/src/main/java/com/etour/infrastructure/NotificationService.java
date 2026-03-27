package com.etour.infrastructure;

import java.util.Map;
import java.util.HashMap;

/**
 * Service for sending notifications to users.
 * Modified to include sendSuccessNotification() method for success notifications.
 */
public class NotificationService {
    
    /**
     * Sends a notification to a user.
     * @param userId the recipient's ID
     * @param message the notification message
     * @param type the notification type (e.g., "SUCCESS", "ERROR")
     * @return true if sent successfully, false otherwise
     */
    public boolean sendNotification(String userId, String message, String type) {
        System.out.println("Notification to " + userId + " [" + type + "]: " + message);
        Map<String, Object> notification = new HashMap<>();
        notification.put("userId", userId);
        notification.put("message", message);
        notification.put("type", type);
        notification.put("timestamp", new java.util.Date());
        logNotification(notification);
        return true;
    }
    
    /**
     * Sends a success notification.
     * @param userId the recipient's ID
     * @param message the success message
     * @return true if sent successfully, false otherwise
     */
    public boolean sendSuccessNotification(String userId, String message) {
        return sendNotification(userId, message, "SUCCESS");
    }
    
    /**
     * Logs a notification for auditing.
     * @param notification a map containing notification details
     */
    public void logNotification(Map<String, Object> notification) {
        System.out.println("Notification logged: " + notification);
    }
}