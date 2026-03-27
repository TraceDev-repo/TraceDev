package com.example.application.ports;

/**
 * Port (Interface) for notification service.
 */
public interface NotificationService {
    boolean sendNotification(String message, String recipient);
}