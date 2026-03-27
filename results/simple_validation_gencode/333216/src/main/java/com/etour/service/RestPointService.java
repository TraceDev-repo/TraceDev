package com.etour.service;

import com.etour.model.RefreshmentPoint;
import com.etour.repository.IRefreshmentPointRepository;
import com.etour.server.IETOURServer;

/**
 * Implementation of IRestPointService.
 * Also checks server connection before performing updates.
 */
public class RestPointService implements IRestPointService {
    private IRefreshmentPointRepository restPointRepository;
    private IETOURServer serverConnection;
    private volatile boolean connectionMonitoringActive = false;

    public RestPointService(IRefreshmentPointRepository restPointRepository, IETOURServer serverConnection) {
        this.restPointRepository = restPointRepository;
        this.serverConnection = serverConnection;
        startConnectionMonitoring();
    }

    @Override
    public RefreshmentPoint getRefreshmentPointById(String id) {
        performPeriodicConnectionCheck();
        System.out.println("Service fetching refreshment point by ID: " + id);
        return restPointRepository.findById(id);
    }

    @Override
    public boolean updateRefreshmentPoint(RefreshmentPoint point) {
        performPeriodicConnectionCheck();
        System.out.println("Service updating refreshment point: " + point.getId());

        // Check server connection before proceeding (Exit Conditions)
        if (!checkServerConnection()) {
            System.out.println("Server connection failed. Update aborted.");
            throw new RuntimeException("OperationFailedException: Server disconnected");
        }

        // Perform the update
        boolean success = restPointRepository.save(point);
        if (success) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update failed in repository.");
        }
        return success;
    }

    /**
     * Checks the connection to the ETour server.
     * @return true if connected, false otherwise.
     */
    public boolean checkServerConnection() {
        System.out.println("Checking server connection...");
        boolean connected = serverConnection.isConnected();
        System.out.println("Server connected: " + connected);
        return connected;
    }

    /**
     * Perform periodic connection check during operations.
     */
    private void performPeriodicConnectionCheck() {
        // Simulate periodic check - in real system would be more sophisticated
        if (!serverConnection.isConnected() && connectionMonitoringActive) {
            System.out.println("Connection interrupted mid-operation. Attempting to reconnect...");
            serverConnection.connect();
        }
    }

    /**
     * Start connection monitoring (simulated).
     */
    private void startConnectionMonitoring() {
        connectionMonitoringActive = true;
        System.out.println("Server connection monitoring started.");
    }
}