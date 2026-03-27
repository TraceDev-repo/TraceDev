package com.example.application.ports;

/**
 * Port (Interface) for ETOUR system adapter.
 */
public interface EtourAdapter {
    boolean sendActivationNotification(String conventionId);
    boolean isConnectionAvailable();
    boolean sendWithRetry(String conventionId, int maxRetries);
}