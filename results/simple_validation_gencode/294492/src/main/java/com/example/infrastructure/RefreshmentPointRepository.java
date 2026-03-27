package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint persistence operations.
 */
public interface RefreshmentPointRepository {
    List<RefreshmentPoint> findAll();
    Optional<RefreshmentPoint> findById(String id);
}