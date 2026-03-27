package com.example.database;

/**
 * Dummy data source class representing a connection to the ETOUR server.
 * Assumption: In a real system, this would manage connection pools, credentials, etc.
 */
public class ETOURDataSource {

    private boolean connected;

    // Constructor
    public ETOURDataSource() {
        this.connected = true; // by default, assume connected
    }

    /**
     * Simulates checking the connection status.
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        // For demonstration, randomly simulate a connection failure with 20% probability.
        if (Math.random() < 0.2) {
            this.connected = false;
        } else {
            this.connected = true;
        }
        return connected;
    }

    /**
     * Allows setting the connection status for testing.
     * @param connected the desired connection state
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}