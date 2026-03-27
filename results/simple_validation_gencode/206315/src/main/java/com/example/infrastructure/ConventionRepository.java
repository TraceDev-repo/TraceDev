package com.example.infrastructure;

import com.example.domain.Convention;

import java.util.Optional;

/**
 * Repository interface for Convention entities.
 */
public interface ConventionRepository {
    Optional<Convention> findById(String id);
    Convention save(Convention convention);
}