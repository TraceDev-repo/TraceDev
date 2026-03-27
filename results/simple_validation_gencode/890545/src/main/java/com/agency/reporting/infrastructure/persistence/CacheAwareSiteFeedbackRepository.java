package com.agency.reporting.infrastructure.persistence;

import com.agency.reporting.application.SearchSiteUseCase;
import com.agency.reporting.domain.SiteFeedback;
import com.agency.reporting.infrastructure.cache.Cache;
import java.util.List;

/**
 * Repository that uses cache and fallback to SearchSiteUseCase.
 */
public class CacheAwareSiteFeedbackRepository implements SiteFeedbackRepository {
    private final Cache cache;
    private final SearchSiteUseCase searchSiteUseCase;

    public CacheAwareSiteFeedbackRepository(Cache cache, SearchSiteUseCase searchSiteUseCase) {
        this.cache = cache;
        this.searchSiteUseCase = searchSiteUseCase;
    }

    @Override
    public List<SiteFeedback> findByLocation(String locationId) {
        // Check cache first
        Object cached = cache.get(locationId);
        if (cached instanceof List) {
            System.out.println("Cache hit for location: " + locationId);
            return (List<SiteFeedback>) cached;
        }

        // Cache miss: use SearchSiteUseCase
        System.out.println("Cache miss for location: " + locationId + ", activating SearchSite use case");
        try {
            List<SiteFeedback> feedbackList = searchSiteUseCase.searchSiteFeedback(locationId);
            if (feedbackList != null) {
                cache.put(locationId, feedbackList);
            }
            return feedbackList;
        } catch (Exception e) {
            System.err.println("Error fetching feedback from external service: " + e.getMessage());
            throw new RuntimeException("Connection interrupted", e);
        }
    }
}