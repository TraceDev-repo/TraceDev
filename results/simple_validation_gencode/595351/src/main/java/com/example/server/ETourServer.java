package com.example.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Represents the ETOUR server data source
public class ETourServer {
    private ConnectionStatus connectionStatus = ConnectionStatus.CONNECTED;
    
    // Enum to represent connection status
    public enum ConnectionStatus {
        CONNECTED, DISCONNECTED, ERROR
    }
    
    // Gets a connection to the ETOUR server
    public Connection getConnection() {
        if (!isConnected()) {
            throw new RuntimeException("ETourServer connection interrupted");
        }
        
        try {
            // In a real implementation, this would return an actual database connection
            // For demonstration, we simulate a connection
            return DriverManager.getConnection("jdbc:mock://localhost:3306/etour");
        } catch (SQLException e) {
            // Simulate connection interruption
            connectionStatus = ConnectionStatus.ERROR;
            throw new RuntimeException("Failed to get connection to ETOUR server", e);
        }
    }
    
    // Checks if connected to the server
    public boolean isConnected() {
        return connectionStatus == ConnectionStatus.CONNECTED;
    }
    
    // Method to simulate connection interruption (for testing)
    public void simulateConnectionInterruption() {
        this.connectionStatus = ConnectionStatus.DISCONNECTED;
    }
    
    // Method to restore connection (for testing)
    public void restoreConnection() {
        this.connectionStatus = ConnectionStatus.CONNECTED;
    }
}