package com.agency;

/**
 * Manages connection to the ETOUR server.
 */
public class ETOURConnectionManager implements IConnectionManager {
    private String serverUrl;
    private int timeout;

    public ETOURConnectionManager(String url, int timeout) {
        this.serverUrl = url;
        this.timeout = timeout;
    }

    @Override
    public boolean checkConnection() {
        // Simulate connection check to server.
        // In reality, this would ping the server or attempt a lightweight request.
        System.out.println("Checking connection to " + serverUrl + " with timeout " + timeout + "ms");
        // For demonstration, assume connection is always successful.
        return true;
    }

    @Override
    public void handleConnectionError() {
        System.err.println("Connection error to " + serverUrl);
        // In a real scenario, this might log the error, alert administrators, etc.
    }

    @Override
    public boolean isServerAvailable() {
        // For simplicity, same as checkConnection.
        return checkConnection();
    }

    /**
     * Attempts to retry the connection.
     * @param maxRetries the maximum number of retries.
     * @return true if connection succeeds within retries, false otherwise.
     */
    public boolean retryConnection(int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            System.out.println("Retry attempt " + (i + 1) + " of " + maxRetries);
            if (checkConnection()) {
                return true;
            }
            try {
                Thread.sleep(1000); // Wait 1 second between retries.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }
}