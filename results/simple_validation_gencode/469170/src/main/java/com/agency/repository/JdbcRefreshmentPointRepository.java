package com.agency.repository;

import com.agency.dto.SearchCriteria;
import com.agency.entity.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of RefreshmentPointRepository.
 */
public class JdbcRefreshmentPointRepository implements RefreshmentPointRepository {
    @Override
    public List<RefreshmentPoint> findAll(SearchCriteria searchCriteria) {
        // Simulating database query with dummy data
        List<RefreshmentPoint> points = new ArrayList<>();
        points.add(new RefreshmentPoint("rp1", "Cafe Central", "Main Concourse"));
        points.add(new RefreshmentPoint("rp2", "Snack Bar", "Platform 2"));
        return points;
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        // Simulating retrieval by ID
        if ("rp1".equals(id)) {
            return Optional.of(new RefreshmentPoint("rp1", "Cafe Central", "Main Concourse"));
        } else if ("rp2".equals(id)) {
            return Optional.of(new RefreshmentPoint("rp2", "Snack Bar", "Platform 2"));
        }
        return Optional.empty();
    }
}