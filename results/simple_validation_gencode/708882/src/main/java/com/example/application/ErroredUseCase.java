package com.example.application;

import java.util.List;

/**
 * Interface for handling error scenarios in use cases.
 */
public interface ErroredUseCase {
    /**
     * Activates error handling with the provided error messages.
     * @param errorMessages list of error messages to process
     */
    void activate(List<String> errorMessages);
}