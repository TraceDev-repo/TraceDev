package com.example.exceptions;

/**
 * Exception thrown when an optimistic locking conflict occurs.
 */
public class OptimisticLockException extends RuntimeException {
    public OptimisticLockException(String message) {
        super(message);
    }
}