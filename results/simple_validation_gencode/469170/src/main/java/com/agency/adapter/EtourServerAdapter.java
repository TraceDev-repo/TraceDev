package com.agency.adapter;

import java.util.concurrent.TimeUnit;

/**
 * Adapter for communicating with the external Etour server.
 */
public class EtourServerAdapter {
    private boolean connected;
    private int retryCount = 0;
    private static final int MAX_RETRIES = 3;

    public EtourServerAdapter() {
        this.connected = false;
    }

    public boolean connect() {
        // Simulate connection attempt with retry strategy
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                // Simulate network delay
                TimeUnit.MILLISECONDS.sleep(100);
                connected = true;
                retryCount = 0;
                return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            } catch (Exception e) {
                retryCount++;
                if (retryCount >= MAX_RETRIES) {
                    connected = false;
                    return false;
                }
                // Backoff before retry
                try {
                    TimeUnit.MILLISECONDS.sleep(200 * (i + 1));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
        }
        connected = false;
        return false;
    }

    public void disconnect() {
        connected = false;
    }

    public boolean notifyBannerDeletion(String bannerId) {
        // Simulate notification to Etour server with proper error recovery
        if (!connected) {
            if (!connect()) {
                return false;
            }
        }
        try {
            // Simulate network request with potential failure
            TimeUnit.MILLISECONDS.sleep(150);
            // 90% success rate simulation
            return Math.random() > 0.1;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            // Log error and attempt reconnect
            disconnect();
            return false;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean reconnect() {
        // Attempt to reconnect with retry logic
        disconnect();
        return connect();
    }

    public int getRetryCount() {
        return retryCount;
    }
}