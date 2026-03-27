package com.example.web.result;

import java.util.Collections;
import java.util.List;

/**
 * Result of image characteristic validation.
 */
public class ImageValidationResult {
    private boolean passed;
    private List<String> errors;

    private ImageValidationResult(boolean passed, List<String> errors) {
        this.passed = passed;
        this.errors = errors != null ? errors : Collections.emptyList();
    }

    public static ImageValidationResult passed() {
        return new ImageValidationResult(true, Collections.emptyList());
    }

    public static ImageValidationResult failed(List<String> errors) {
        return new ImageValidationResult(false, errors);
    }

    public boolean hasPassed() {
        return passed;
    }

    public List<String> getErrors() {
        return errors;
    }
}