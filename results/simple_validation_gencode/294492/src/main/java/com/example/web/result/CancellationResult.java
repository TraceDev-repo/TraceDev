package com.example.web.result;

/**
 * Result of a cancellation operation.
 */
public class CancellationResult {
    private boolean cancelled;
    private boolean cleanupPerformed;

    private CancellationResult(boolean cancelled, boolean cleanupPerformed) {
        this.cancelled = cancelled;
        this.cleanupPerformed = cleanupPerformed;
    }

    public static CancellationResult success(boolean cleanupPerformed) {
        return new CancellationResult(true, cleanupPerformed);
    }

    public static CancellationResult failure() {
        return new CancellationResult(false, false);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public boolean isCleanupPerformed() {
        return cleanupPerformed;
    }
}