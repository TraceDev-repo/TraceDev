package com.example.infrastructure;

/**
 * Validates database state and handles empty database scenarios gracefully.
 */
public class DataValidationService {
    private final CulturalObjectRepository objectRepository;

    public DataValidationService(CulturalObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    public boolean hasData() {
        return objectRepository.checkDataAvailability();
    }

    public boolean checkDataIntegrity() {
        // Simplified: just check connection and that data exists.
        return objectRepository.checkConnection() && objectRepository.hasData();
    }

    public DataStatistics getDataStatistics() {
        // Mock implementation: assume we cannot get real stats from repository.
        return new DataStatistics(0, new java.util.Date());
    }
}