package com.example.repository;

import com.example.refreshmentpoint.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint entities.
 */
public interface RefreshmentPointRepository {
    Optional<RefreshmentPoint> findById(String id);
    void deleteById(String id);
    List<RefreshmentPoint> findAll();
}