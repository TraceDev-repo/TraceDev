package com.example.infrastructure;

import com.example.domain.TouristAccount;

/**
 * Implementation of notification service.
 * Simulates sending notifications via ETOUR server.
 */
public class NotificationServiceImpl implements INotificationService {

    private ETOURServerConnection connection;
    private PerformanceMonitor performanceMonitor;

    public NotificationServiceImpl(ETOURServerConnection connection) {
        this.connection = connection;
    }

    public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public void notifyStatusChange(TouristAccount account) {
        // Performance constraint validation
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Connection check (simulated) - explicit check as per design
        if (connection == null) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("ETOUR server connection not initialized.");
        }
        if (!connection.isConnected()) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("ETOUR server connection lost for notification.");
        }

        // Simulate notification preparation and sending
        if (connection != null) {
            // connection.executeQuery("GET_NOTIFICATION_CONFIG");
        }

        System.out.println("Notification sent for account: " + account.getAccountId() +
                " to email: " + account.getTouristDetails().getEmail());
    }
}