package com.etour.infrastructure;

/**
 * Manages network connectivity checks and reconnection.
 */
public class NetworkConnectionManager {
    private String connectionStatus = "CONNECTED";
    
    public boolean checkConnection() {
        // Simulate connection check: 90% chance of being connected.
        return Math.random() > 0.1;
    }
    
    public boolean reconnect() {
        // Simulate reconnection attempt.
        connectionStatus = "CONNECTED";
        return true;
    }
    
    public String getConnectionStatus() {
        return connectionStatus;
    }
}