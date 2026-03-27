package com.example.infrastructure;

/**
 * Enforces quality requirement: Search completes within set time.
 * Configurable through TimeoutConfiguration.
 */
public class TimeoutPolicy {
    private final long maxSearchTimeMs;
    private final TimeoutConfiguration timeoutConfiguration;

    public TimeoutPolicy(long maxSearchTimeMs, TimeoutConfiguration timeoutConfiguration) {
        this.maxSearchTimeMs = maxSearchTimeMs;
        this.timeoutConfiguration = timeoutConfiguration;
    }

    /**
     * Enforces timeout for an operation.
     * @param startTime operation start time (milliseconds)
     * @param operationName name of the operation (used for config lookup)
     */
    public void enforceTimeout(long startTime, String operationName) {
        long allowed = timeoutConfiguration.getTimeout(operationName);
        if (System.currentTimeMillis() - startTime > allowed) {
            throw new RuntimeException("Timeout exceeded for operation: " + operationName);
        }
    }

    public long getRemainingTime(long startTime) {
        long elapsed = System.currentTimeMillis() - startTime;
        return Math.max(0, maxSearchTimeMs - elapsed);
    }

    public boolean hasExceededTimeout(long startTime) {
        return System.currentTimeMillis() - startTime > maxSearchTimeMs;
    }

    /**
     * Validates the timeout configuration (as per class diagram).
     */
    public boolean validateTimeoutConfiguration() {
        return timeoutConfiguration.validateAllTimeouts();
    }
}