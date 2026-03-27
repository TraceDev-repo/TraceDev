package com.example.infrastructure.persistence;

import com.example.application.ports.ConventionRepository;
import com.example.domain.Convention;
import com.example.domain.ConventionRequest;

/**
 * Adapter implementation for ConventionRepository.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    // In a real implementation, a DataSource would be injected.

    @Override
    public Convention findById(String id) {
        // Simulate database lookup
        // For demonstration, return a new Convention with the given ID
        return new Convention(id, "RP-" + id);
    }

    @Override
    public void save(Convention convention) {
        // Simulate saving to database
        System.out.println("Saving convention: " + convention.getId());
    }

    @Override
    public ConventionRequest findRequestByConventionId(String conventionId) {
        // Simulate database lookup
        return new ConventionRequest("REQ-" + conventionId, conventionId);
    }

    @Override
    public void saveRequest(ConventionRequest request) {
        // Simulate saving to database
        System.out.println("Saving convention request: " + request.getId());
    }
}