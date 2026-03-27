package com.example.adapters.views;

import java.util.List;

/**
 * Represents the result of form validation.
 */
public class SearchFormValidationResult {
    private final boolean success;
    private final List<String> errors;

    public SearchFormValidationResult(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getErrors() {
        return errors;
    }
}