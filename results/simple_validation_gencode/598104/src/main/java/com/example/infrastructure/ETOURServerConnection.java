package com.example.infrastructure;

/**
 * Simulates connection to the ETOUR server.
 * Used by repository, notification, and confirmation services.
 */
public class ETOURServerConnection {
    private String connectionId;
    private ConnectionStatus status;

    public ETOURServerConnection() {
        this.status = ConnectionStatus.DISCONNECTED;
    }

    public boolean connect() {
        this.status = ConnectionStatus.CONNECTED;
        System.out.println("ETOUR server connected.");
        return true;
    }

    public void disconnect() {
        this.status = ConnectionStatus.DISCONNECTED;
        System.out.println("ETOUR server disconnected.");
    }

    public boolean isConnected() {
        // Simulate occasional disconnection
        return status == ConnectionStatus.CONNECTED;
    }

    public String executeQuery(String query) {
        if (!isConnected()) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("Cannot execute query: not connected.");
        }
        System.out.println("Executing query: " + query);
        return "MockResult";
    }

    public boolean updateData(Object data) {
        if (!isConnected()) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("Cannot update data: not connected.");
        }
        System.out.println("Updating data: " + data.toString());
        return true;
    }

    public ConnectionStatus getStatus() {
        return status;
    }
}