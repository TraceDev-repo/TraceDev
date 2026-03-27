package com.example.infrastructure;

/**
 * Implementation of NotificationDisplayService.
 * Uses UIAdapter to display notifications.
 */
public class NotificationDisplayServiceImpl implements NotificationDisplayService {
    private UIAdapter uiAdapter;

    public NotificationDisplayServiceImpl(UIAdapter uiAdapter) {
        this.uiAdapter = uiAdapter;
    }

    @Override
    public void displayNotification(String notificationId, String message) {
        uiAdapter.showNotification(notificationId, message);
    }
}