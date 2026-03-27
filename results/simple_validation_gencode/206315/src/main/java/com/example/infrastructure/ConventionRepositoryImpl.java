package com.example.infrastructure;

import com.example.domain.Convention;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple in-memory implementation of ConventionRepository.
 * Assumes DataSource is abstracted for simplicity.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    private Map<String, Convention> storage = new HashMap<>();

    public ConventionRepositoryImpl() {
        // Initialize with some dummy data for demonstration
        Convention convention = new Convention("conv1", "JavaOne");
        convention.addRefreshmentPoint(new com.example.domain.RefreshmentPoint("rp1", "Main Hall", 5));
        storage.put("conv1", convention);
    }

    @Override
    public Optional<Convention> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Convention save(Convention convention) {
        storage.put(convention.getId(), convention);
        return convention;
    }
}