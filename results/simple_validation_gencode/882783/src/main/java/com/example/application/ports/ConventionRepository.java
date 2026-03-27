package com.example.application.ports;

import com.example.domain.Convention;
import com.example.domain.ConventionRequest;

/**
 * Port (Interface) for Convention repository operations.
 */
public interface ConventionRepository {
    Convention findById(String id);
    void save(Convention convention);
    ConventionRequest findRequestByConventionId(String conventionId);
    void saveRequest(ConventionRequest request);
}