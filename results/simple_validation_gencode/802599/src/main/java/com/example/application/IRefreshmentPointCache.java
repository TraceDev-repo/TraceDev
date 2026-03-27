package com.example.application;

import com.example.domain.RefreshmentPoint;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Cache interface for performance optimization.
 */
public interface IRefreshmentPointCache {
    Optional<RefreshmentPoint> get(Long id);
    void put(Long id, RefreshmentPoint point);
    void invalidate(Long id);
    Optional<LocalDateTime> getLastUpdated(Long id);
}