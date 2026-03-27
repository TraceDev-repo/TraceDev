
package com.example.application;

import com.example.domain.CulturalObject;
import com.example.infrastructure.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service that handles the search use case.
 * Implements the QueryHandler interface.
 */
public class SearchCulturalObjectsQueryHandler implements QueryHandler<SearchCulturalObjectsQuery, SearchResultDTO> {
    private final CulturalObjectRepository objectRepository;
    private final TimeoutPolicy timeoutPolicy;
    private final AuthenticationService authService;
    private final SearchAvailabilityService searchAvailabilityService;
    private final DataValidationService dataValidationService;

    public SearchCulturalObjectsQueryHandler(CulturalObjectRepository objectRepository,
                                            TimeoutPolicy timeoutPolicy,
                                            AuthenticationService authService,
                                            SearchAvailabilityService searchAvailabilityService,
                                            DataValidationService dataValidationService) {
        this.objectRepository = objectRepository;
        this.timeoutPolicy = timeoutPolicy;
        this.authService = authService;
        this.searchAvailabilityService = searchAvailabilityService;
        this.dataValidationService = dataValidationService;
    }

    @Override
    public SearchResultDTO handle(SearchCulturalObjectsQuery query) {
        // Proactive connection health monitoring (step 4.1 in sequence diagram)
        ConnectionMonitor monitor = ((EtourServerObjectRepository) objectRepository).getConnectionMonitor();
        ConnectionStatus connectionStatus = monitor.checkConnectionHealth();
        if (!connectionStatus.isHealthy()) {
            // Return ConnectionErrorResultDTO as per sequence diagram
            return new ConnectionErrorResultDTO("ETOUR server unavailable: connection unhealthy");
        }

        // Quick data availability re-check (step 5)
        if (!dataValidationService.hasData()) {
            // Return empty result DTO as per sequence diagram alternative flow
            return new SearchResultDTO(List.of(), 0, 0);
        }

        long startTimer = System.currentTimeMillis();
        timeoutPolicy.enforceTimeout(startTimer, "search");

        // Convert query to search criteria (assumed mapping)
        SearchCriteria criteria = new SearchCriteria(
                query.getKeyword(),
                query.getObjectType(),
                query.getLocation(),
                query.getDateRange()
        );

        // Execute repository search (step 6)
        List<CulturalObject> culturalObjects;
        try {
            culturalObjects = objectRepository.searchByCriteria(criteria);
        } catch (Exception e) {
            // Handle connection interruption exception flow
            if (e.getMessage() != null && e.getMessage().contains("connection")) {
                return new ConnectionErrorResultDTO("Connection lost: " + e.getMessage());
            }
            throw e;
        }

        // Check timeout after search
        if (timeoutPolicy.hasExceededTimeout(startTimer)) {
            // Return TimeoutResultDTO instead of throwing exception
            return new TimeoutResultDTO("Search timeout exceeded");
        }

        long searchTimeMs = System.currentTimeMillis() - startTimer;

        // Map domain objects to DTOs
        List<CulturalObjectDTO> dtos = culturalObjects.stream()
                .map(CulturalObject::mapToDTO)
                .collect(Collectors.toList());

        // Calculate metadata
        int totalCount = dtos.size();

        return new SearchResultDTO(dtos, totalCount, searchTimeMs);
    }

    /**
     * Helper method to check timeout (as per class diagram).
     */
    public void checkTimeout(long startTime) {
        if (timeoutPolicy.hasExceededTimeout(startTime)) {
            // Return TimeoutResultDTO instead of throwing exception
            throw new RuntimeException("Operation timeout");
        }
    }
}
