package com.example.utility;

/**
 * Utility class for managing server connections.
 */
public class ServerConnectionManager {
    private boolean isConnected;
    private ConnectionState connectionState;

    public enum ConnectionState {
        CONNECTED, DISCONNECTED, RECONNECTING
    }

    public ServerConnectionManager() {
        this.isConnected = true; // Start connected for demo
        this.connectionState = ConnectionState.CONNECTED;
    }

    /**
     * Checks the current connection status.
     * @return true if connected, false otherwise
     */
    public boolean checkConnection() {
        // Simulate occasional disconnection (10% chance)
        if (Math.random() < 0.1) {
            isConnected = false;
            connectionState = ConnectionState.DISCONNECTED;
        }
        System.out.println("ConnectionManager: Connection check - " + 
                          (isConnected ? "Connected" : "Disconnected"));
        return isConnected;
    }

    /**
     * Handles connection interruption.
     */
    public void handleInterruption() {
        System.out.println("ConnectionManager: Handling connection interruption");
        connectionState = ConnectionState.DISCONNECTED;
        isConnected = false;
    }

    /**
     * Attempts to reconnect to the server.
     * @return true if reconnection successful, false otherwise
     */
    public boolean attemptReconnection() {
        System.out.println("ConnectionManager: Attempting reconnection...");
        // Simulate reconnection attempt (80% success rate)
        boolean success = Math.random() < 0.8;
        if (success) {
            isConnected = true;
            connectionState = ConnectionState.CONNECTED;
            System.out.println("ConnectionManager: Reconnection successful");
        } else {
            System.out.println("ConnectionManager: Reconnection failed");
        }
        return success;
    }

    public ConnectionState getConnectionState() {
        return connectionState;
    }

    // For testing
    public void setConnected(boolean connected) {
        isConnected = connected;
        connectionState = connected ? ConnectionState.CONNECTED : ConnectionState.DISCONNECTED;
    }
}