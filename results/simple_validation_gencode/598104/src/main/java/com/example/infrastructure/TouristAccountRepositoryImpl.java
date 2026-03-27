package com.example.infrastructure;

import com.example.domain.TouristAccount;
import com.example.domain.TouristDetails;
import com.example.domain.AccountStatus;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory implementation of tourist account repository.
 * In a real scenario, this would use a database via ETOURServerConnection.
 */
public class TouristAccountRepositoryImpl implements ITouristAccountRepository {

    private Map<String, TouristAccount> accountStore = new HashMap<>();
    private ETOURServerConnection connection; // Would be used for real DB operations
    private PerformanceMonitor performanceMonitor;

    public TouristAccountRepositoryImpl(ETOURServerConnection connection) {
        this.connection = connection;
        // Initialize with some dummy data for demonstration
        TouristDetails details1 = new TouristDetails("John Doe", "john@example.com");
        TouristAccount acc1 = new TouristAccount("TA001", details1);
        acc1.enable();
        accountStore.put("TA001", acc1);

        TouristDetails details2 = new TouristDetails("Jane Smith", "jane@example.com");
        TouristAccount acc2 = new TouristAccount("TA002", details2);
        accountStore.put("TA002", acc2);
    }

    public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public Optional<TouristAccount> findById(String accountId) {
        // Performance constraint validation
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Connection check (simulated) - mandatory as per design
        if (connection == null) {
            throw new ConnectionLostException("ETOUR server connection not initialized.");
        }
        if (!connection.isConnected()) {
            throw new ConnectionLostException("ETOUR server connection lost.");
        }

        // Simulate query execution
        if (connection != null) {
            // connection.executeQuery("SELECT * FROM accounts WHERE id = ?");
        }

        return Optional.ofNullable(accountStore.get(accountId));
    }

    @Override
    public void save(TouristAccount account) {
        // Performance constraint validation
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Connection check (simulated) - mandatory as per design
        if (connection == null) {
            throw new ConnectionLostException("ETOUR server connection not initialized.");
        }
        if (!connection.isConnected()) {
            throw new ConnectionLostException("ETOUR server connection lost during save.");
        }

        // Simulate update operation
        if (connection != null) {
            // boolean success = connection.updateData(account);
            // if (!success) throw new ConnectionException("Update failed.");
        }

        accountStore.put(account.getAccountId(), account);
        System.out.println("Account saved: " + account.getAccountId());
    }

    public static class ConnectionLostException extends RuntimeException {
        public ConnectionLostException(String message) {
            super(message);
        }
    }
}