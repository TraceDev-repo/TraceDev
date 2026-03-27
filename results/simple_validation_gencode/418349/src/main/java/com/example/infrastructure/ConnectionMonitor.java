package com.example.infrastructure;

import java.util.Date;
import java.util.List;

/**
 * Monitors connection health to the ETOUR server.
 */
public class ConnectionMonitor implements CulturalObjectRepository {
    private Date lastHeartbeat;
    private final int heartbeatInterval; // milliseconds
    private final EtourApiClient apiClient;

    public ConnectionMonitor(EtourApiClient apiClient, int heartbeatInterval) {
        this.apiClient = apiClient;
        this.heartbeatInterval = heartbeatInterval;
        this.lastHeartbeat = new Date();
    }

    /**
     * Sends a heartbeat to the ETOUR server.
     */
    public boolean sendHeartbeat() {
        try {
            boolean success = apiClient.sendHeartbeat();
            if (success) {
                lastHeartbeat = new Date();
            }
            return success;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks the overall connection health.
     */
    public ConnectionStatus checkConnectionHealth() {
        long latencyMs = 0;
        boolean healthy = false;
        Date checkTime = new Date();
        try {
            long start = System.currentTimeMillis();
            healthy = sendHeartbeat();
            latencyMs = System.currentTimeMillis() - start;
        } catch (Exception e) {
            healthy = false;
        }
        Date lastSuccessful = healthy ? checkTime : lastHeartbeat;
        return new ConnectionStatus(healthy, latencyMs, lastSuccessful);
    }

    public Date getLastHeartbeatTime() {
        return lastHeartbeat;
    }

    // Implement CulturalObjectRepository interface methods (stub implementations)
    @Override
    public List<com.example.domain.CulturalObject> searchByCriteria(SearchCriteria criteria) {
        throw new UnsupportedOperationException("ConnectionMonitor does not support search operations");
    }

    @Override
    public List<com.example.domain.CulturalObject> findByKeyword(String keyword) {
        throw new UnsupportedOperationException("ConnectionMonitor does not support search operations");
    }

    @Override
    public boolean hasData() {
        // Assume no data since this is a monitor, not a repository.
        return false;
    }

    @Override
    public boolean checkConnection() {
        ConnectionStatus status = checkConnectionHealth();
        return status.isHealthy();
    }

    @Override
    public boolean checkDataAvailability() {
        return hasData();
    }
}