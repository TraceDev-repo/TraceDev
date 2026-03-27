package com.example.connection;

/**
 * Interface for listening to server disconnection events.
 */
public interface DisconnectionListener {
    /**
     * Called when a disconnection is detected.
     */
    void onDisconnectionDetected();
}