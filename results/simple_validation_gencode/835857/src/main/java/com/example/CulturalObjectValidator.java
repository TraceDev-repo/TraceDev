package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Validates cultural object creation requests.
 */
public class CulturalObjectValidator {
    private CulturalObjectRepository repository;

    public CulturalObjectValidator(CulturalObjectRepository repository) {
        this.repository = repository;
    }

    /**
     * Performs full validation for creation.
     * @param request the creation request.
     * @throws ValidationException if any validation fails.
     */
    public void validateForCreation(CreateCulturalObjectRequest request) {
        validateRequiredFields(request);
        validateDataFormats(request);
        checkBusinessRules(request);
        checkForDuplicates(request);
    }

    /**
     * Validates that all required fields are present.
     */
    private void validateRequiredFields(CreateCulturalObjectRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new ValidationException("Name is required.");
        }
        if (request.getType() == null || request.getType().trim().isEmpty()) {
            throw new ValidationException("Type is required.");
        }
        if (request.getLocation() == null || request.getLocation().trim().isEmpty()) {
            throw new ValidationException("Location is required.");
        }
        // Description might be optional, so no check.
    }

    /**
     * Validates data formats (e.g., length, allowed characters).
     */
    private void validateDataFormats(CreateCulturalObjectRequest request) {
        if (request.getName().length() > 100) {
            throw new ValidationException("Name cannot exceed 100 characters.");
        }
        if (request.getLocation().length() > 200) {
            throw new ValidationException("Location cannot exceed 200 characters.");
        }
        // Additional format checks can be added.
    }

    /**
     * Checks business rules (e.g., type must be from a predefined list).
     */
    private void checkBusinessRules(CreateCulturalObjectRequest request) {
        // Example: allowed types
        String[] allowedTypes = {"MONUMENT", "ARTWORK", "HISTORIC_SITE", "ARCHIVE"};
        boolean validType = false;
        for (String allowed : allowedTypes) {
            if (allowed.equalsIgnoreCase(request.getType())) {
                validType = true;
                break;
            }
        }
        if (!validType) {
            throw new ValidationException("Type must be one of: MONUMENT, ARTWORK, HISTORIC_SITE, ARCHIVE");
        }
    }

    /**
     * Checks for duplicate cultural objects based on unique constraints.
     * Unique constraint: name + type + location combination must be unique.
     */
    private void checkForDuplicates(CreateCulturalObjectRequest request) {
        Map<String, Object> uniqueProperties = new HashMap<>();
        uniqueProperties.put("name", request.getName());
        uniqueProperties.put("type", request.getType());
        uniqueProperties.put("location", request.getLocation());

        if (repository.existsByUniqueProperties(uniqueProperties)) {
            throw new ValidationException("A cultural object with the same name, type, and location already exists.");
        }
    }
}