package com.etour.server;

/**
 * Implementation of IETOURServer for connecting to ETour server.
 */
public class ETourServerConnection implements IETOURServer {
    private String serverUrl;
    private boolean connectionStatus;

    public ETourServerConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connectionStatus = false;
    }

    @Override
    public boolean connect() {
        System.out.println("Connecting to ETour server at: " + serverUrl);
        // Simulate connection
        connectionStatus = true;
        System.out.println("Connected to server.");
        return true;
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from ETour server.");
        connectionStatus = false;
    }

    @Override
    public boolean isConnected() {
        return connectionStatus;
    }

    @Override
    public boolean sendData(Object data) {
        System.out.println("Sending data to server: " + data);
        // Simulate data transmission
        return connectionStatus;
    }
}