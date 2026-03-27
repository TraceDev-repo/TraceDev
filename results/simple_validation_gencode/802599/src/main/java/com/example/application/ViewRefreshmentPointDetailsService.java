package com.example.application;

import com.example.domain.RefreshmentPoint;
import com.example.ui.RefreshmentPointDetailsDTO;
import com.example.domain.ServiceException;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application Service (Use Case Controller) for retrieving refreshment point details.
 */
public class ViewRefreshmentPointDetailsService {
    private IRefreshmentPointRepository repository;
    private IRefreshmentPointCache cache;
    private ExecutorService backgroundExecutor = Executors.newSingleThreadExecutor();

    public ViewRefreshmentPointDetailsService(IRefreshmentPointRepository repository, IRefreshmentPointCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    /**
     * Main method to get refreshment point details with cache-first strategy.
     * @param id the refreshment point ID
     * @return DTO containing details
     * @throws ServiceException if data cannot be retrieved
     */
    public RefreshmentPointDetailsDTO getRefreshmentPointDetails(Long id) {
        try {
            RefreshmentPoint point = fetchWithFallback(id);
            return new RefreshmentPointDetailsDTO(point);
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            } else {
                throw new ServiceException("SVC_001", "Failed to retrieve refreshment point details", e);
            }
        }
    }

    /**
     * Fetches data with cache fallback strategy.
     * Implements freshness-aware caching as per sequence diagram.
     */
    private RefreshmentPoint fetchWithFallback(Long id) {
        Optional<RefreshmentPoint> cachedOpt = cache.get(id);
        if (cachedOpt.isPresent()) {
            RefreshmentPoint cachedItem = cachedOpt.get();
            if (!isCacheStale(cachedItem)) {
                // Cache is fresh, use it and possibly refresh in background if approaching staleness.
                triggerBackgroundRefreshIfNeeded(id);
                return cachedItem;
            }
        }
        // Cache miss or stale: load from server
        RefreshmentPoint freshPoint = repository.loadDataFromServer(id);
        cache.put(id, freshPoint);
        return freshPoint;
    }

    /**
     * Checks if cached item is stale (older than 30 minutes).
     */
    boolean isCacheStale(RefreshmentPoint cachedItem) {
        Long id = cachedItem.getId();
        Optional<LocalDateTime> lastUpdated = cache.getLastUpdated(id);
        if (!lastUpdated.isPresent()) {
            return true;
        }
        Duration age = Duration.between(lastUpdated.get(), LocalDateTime.now());
        return age.toMinutes() > 30;   // TTL = 30 minutes
    }

    /**
     * Triggers an asynchronous refresh if cache is older than 25 minutes.
     */
    private void triggerBackgroundRefreshIfNeeded(Long id) {
        Optional<LocalDateTime> lastUpdated = cache.getLastUpdated(id);
        if (!lastUpdated.isPresent()) {
            return;
        }
        Duration age = Duration.between(lastUpdated.get(), LocalDateTime.now());
        if (age.toMinutes() > 25) {
            backgroundExecutor.submit(() -> {
                try {
                    RefreshmentPoint fresh = repository.loadDataFromServer(id);
                    cache.put(id, fresh);
                } catch (Exception e) {
                    // Log the failure; do not propagate because this is a background refresh.
                    System.err.println("Background refresh failed for id " + id + ": " + e.getMessage());
                }
            });
        }
    }
}