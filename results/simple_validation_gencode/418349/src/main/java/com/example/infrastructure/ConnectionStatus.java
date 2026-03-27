package com.example.infrastructure;

import java.util.Date;

/**
 * Value object representing connection status.
 */
public class ConnectionStatus {
    private final boolean isHealthy;
    private final long latencyMs;
    private final Date lastSuccessfulCheck;

    public ConnectionStatus(boolean isHealthy, long latencyMs, Date lastSuccessfulCheck) {
        this.isHealthy = isHealthy;
        this.latencyMs = latencyMs;
        this.lastSuccessfulCheck = lastSuccessfulCheck;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public long getLatencyMs() {
        return latencyMs;
    }

    public Date getLastSuccessfulCheck() {
        return lastSuccessfulCheck;
    }
}