package com.example.infrastructure.etour;

import com.example.application.ports.EtourAdapter;

/**
 * Adapter for communicating with the ETOUR system via REST.
 */
public class EtourRestAdapter implements EtourAdapter {
    @Override
    public boolean sendActivationNotification(String conventionId) {
        System.out.println("Sending activation notification to ETOUR for convention: " + conventionId);
        // Simulate success 80% of the time
        return Math.random() > 0.2;
    }

    @Override
    public boolean isConnectionAvailable() {
        // Simulate connection check
        return true;
    }

    @Override
    public boolean sendWithRetry(String conventionId, int maxRetries) {
        for (int i = 0; i < maxRetries; i++) {
            if (sendActivationNotification(conventionId)) {
                return true;
            }
            System.out.println("Retry " + (i + 1) + " for convention: " + conventionId);
            try {
                Thread.sleep(1000); // wait before retry
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }
}