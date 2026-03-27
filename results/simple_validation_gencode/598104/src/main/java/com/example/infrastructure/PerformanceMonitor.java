package com.example.infrastructure;

/**
 * Monitors performance to satisfy requirement REQ-QUAL-01 (2-second timeout).
 */
public class PerformanceMonitor {

    private long startTime;
    private long timeoutMillis;

    public PerformanceMonitor(long timeoutSeconds) {
        this.timeoutMillis = timeoutSeconds * 1000;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Checks if the operation has exceeded the timeout.
     * Throws TimeoutException if elapsed time > timeout.
     */
    public void checkTimeout() {
        long elapsed = getElapsedTimeMillis();
        if (elapsed > timeoutMillis) {
            throw new TimeoutException("Operation exceeded timeout of " + (timeoutMillis / 1000) + " seconds.");
        }
    }

    public long getElapsedTime() {
        return getElapsedTimeMillis() / 1000; // return seconds
    }

    private long getElapsedTimeMillis() {
        return System.currentTimeMillis() - startTime;
    }

    public static class TimeoutException extends RuntimeException {
        public TimeoutException(String message) {
            super(message);
        }
    }
}