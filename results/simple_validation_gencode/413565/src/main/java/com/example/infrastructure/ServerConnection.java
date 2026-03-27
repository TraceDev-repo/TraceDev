package com.example.infrastructure;

/**
 * Simulates server connection monitoring.
 */
public class ServerConnection {
    private boolean isConnected = true;

    public boolean checkConnection() {
        return isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void monitorConnection() {
        // Periodically check connection (simulated).
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    if (!isConnected) {
                        handleDisconnect();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    public void handleDisconnect() {
        System.out.println("Disconnect handled: attempting reconnection...");
        // Simulate reconnection.
        isConnected = true;
    }
}