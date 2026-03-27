package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;
import com.example.domain.SearchSpecification;
import com.example.domain.repository.RefreshmentPointRepository;

import java.util.List;
import java.util.Optional;

/**
 * Infrastructure implementation of RefreshmentPointRepository that interacts with the eTour server.
 * This class belongs to the Infrastructure Layer.
 */
public class EServerRefreshmentPointRepository implements RefreshmentPointRepository {
    private final EServerApiClient apiClient;
    private final RefreshmentPointCache cache;

    public EServerRefreshmentPointRepository(EServerApiClient apiClient, RefreshmentPointCache cache) {
        this.apiClient = apiClient;
        this.cache = cache;
    }

    @Override
    public List<RefreshmentPoint> findAllBySpecification(SearchSpecification spec) {
        // Convert domain specification to infrastructure SearchCriteria
        SearchCriteria criteria = new SearchCriteria();
        criteria.setLocation(spec.getLocation());
        criteria.setPointType(spec.getPointType());
        criteria.setRatingThreshold(spec.getRatingThreshold());
        criteria.setRequiredAmenities(spec.getRequiredAmenities());

        // Generate cache key
        String cacheKey = criteria.toCacheKey();

        // Try to get from cache first
        Optional<List<RefreshmentPoint>> cachedPoints = cache.get(cacheKey);
        if (cachedPoints.isPresent()) {
            System.out.println("Cache hit for key: " + cacheKey);
            return cachedPoints.get();
        }

        System.out.println("Cache miss for key: " + cacheKey + ". Fetching from eTour server.");

        try {
            // Fetch from eTour server
            List<RefreshmentPoint> points = apiClient.searchPoints(criteria);

            // Cache the results
            cache.put(cacheKey, points);

            return points;
        } catch (ConnectionException e) {
            // Log the connection error and return empty list as per sequence diagram
            System.err.println("Connection to eTour server interrupted: " + e.getMessage());
            System.out.println("Returning empty list due to connection interruption.");
            return List.of();
        }
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        try {
            return Optional.ofNullable(apiClient.fetchPointDetails(id));
        } catch (ConnectionException e) {
            System.err.println("Connection error while fetching point details for id: " + id);
            return Optional.empty();
        }
    }
}