package com.agency.circuitbreaker;

import java.util.concurrent.Callable;

/**
 * Simple circuit breaker for managing failures in external service calls.
 */
public class CircuitBreaker {
    private String state = "CLOSED";
    private int failureThreshold = 3;
    private long timeout = 5000; // 5 seconds
    private int failureCount = 0;

    public Result execute(Callable<Boolean> callable) {
        if (isOpen()) {
            return new Result(false, "Circuit breaker is OPEN");
        }

        try {
            Boolean success = callable.call();
            if (success) {
                reset(); // Reset failures on success
                return new Result(true, "Success");
            } else {
                failureCount++;
                if (failureCount >= failureThreshold) {
                    state = "OPEN";
                }
                return new Result(false, "Operation failed");
            }
        } catch (Exception e) {
            failureCount++;
            if (failureCount >= failureThreshold) {
                state = "OPEN";
            }
            return new Result(false, "Exception: " + e.getMessage());
        }
    }

    public boolean isOpen() {
        return "OPEN".equals(state);
    }

    public void reset() {
        state = "CLOSED";
        failureCount = 0;
    }

    public static class Result {
        private boolean success;
        private String message;

        public Result(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}