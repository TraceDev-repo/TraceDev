package com.example.application;

import com.example.domain.RefreshmentPoint;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache implementation with timestamps for freshness checking.
 */
public class RefreshmentPointCache implements IRefreshmentPointCache {
    private ConcurrentHashMap<Long, RefreshmentPoint> cacheMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, LocalDateTime> lastUpdatedMap = new ConcurrentHashMap<>();

    @Override
    public Optional<RefreshmentPoint> get(Long id) {
        return Optional.ofNullable(cacheMap.get(id));
    }

    @Override
    public void put(Long id, RefreshmentPoint point) {
        cacheMap.put(id, point);
        lastUpdatedMap.put(id, LocalDateTime.now());
    }

    @Override
    public void invalidate(Long id) {
        cacheMap.remove(id);
        lastUpdatedMap.remove(id);
    }

    @Override
    public Optional<LocalDateTime> getLastUpdated(Long id) {
        return Optional.ofNullable(lastUpdatedMap.get(id));
    }
}