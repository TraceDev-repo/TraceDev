package com.example.infrastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurable timeout mechanism.
 */
public class TimeoutConfiguration {
    private final long defaultTimeoutMs;
    private final Map<String, Long> perOperationTimeouts;

    public TimeoutConfiguration(long defaultTimeoutMs) {
        this.defaultTimeoutMs = defaultTimeoutMs;
        this.perOperationTimeouts = new HashMap<>();
    }

    public long getTimeout(String operationName) {
        return perOperationTimeouts.getOrDefault(operationName, defaultTimeoutMs);
    }

    public void setTimeout(String operationName, long timeoutMs) {
        perOperationTimeouts.put(operationName, timeoutMs);
    }

    public boolean validateAllTimeouts() {
        // All timeouts must be positive.
        if (defaultTimeoutMs <= 0) return false;
        for (Long timeout : perOperationTimeouts.values()) {
            if (timeout <= 0) return false;
        }
        return true;
    }
}