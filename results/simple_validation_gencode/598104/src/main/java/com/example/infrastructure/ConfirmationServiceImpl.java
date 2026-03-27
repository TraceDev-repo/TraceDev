package com.example.infrastructure;

import com.example.domain.AccountStatus;

/**
 * Implementation of confirmation service that simulates interaction with a confirmation dialog.
 */
public class ConfirmationServiceImpl implements IConfirmationService {

    private ETOURServerConnection connection;
    private PerformanceMonitor performanceMonitor;

    public ConfirmationServiceImpl(ETOURServerConnection connection, PerformanceMonitor performanceMonitor) {
        this.connection = connection;
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public boolean requestConfirmation(String accountId, AccountStatus newStatus) {
        // Performance constraint validation
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Connection check (simulated) - explicit check as per design
        if (connection == null) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("ETOUR server connection not initialized.");
        }
        if (!connection.isConnected()) {
            throw new TouristAccountRepositoryImpl.ConnectionLostException("ETOUR server connection lost for confirmation.");
        }

        // In a real UI scenario, this would show a dialog and wait for user input.
        // For demonstration, we simulate a dialog that always returns true.
        ConfirmationDialog dialog = new ConfirmationDialog(accountId, newStatus);
        dialog.setPerformanceMonitor(performanceMonitor);
        return dialog.showDialog();
    }
}