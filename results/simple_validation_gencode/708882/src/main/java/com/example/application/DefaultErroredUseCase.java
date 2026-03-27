package com.example.application;

import java.util.List;

/**
 * Default implementation of ErroredUseCase.
 * Simulates error handling behavior.
 */
public class DefaultErroredUseCase implements ErroredUseCase {
    @Override
    public void activate(List<String> errorMessages) {
        System.out.println("ErroredUseCase activated with errors: " + errorMessages);
        // In a real application, this might log errors, send notifications, etc.
    }
}