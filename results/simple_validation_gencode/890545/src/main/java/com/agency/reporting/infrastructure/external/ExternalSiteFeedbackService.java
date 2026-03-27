package com.agency.reporting.infrastructure.external;

import com.agency.reporting.application.SearchSiteUseCase;
import com.agency.reporting.domain.SiteFeedback;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Adapter for external site feedback service.
 */
public class ExternalSiteFeedbackService implements SearchSiteUseCase {
    @Override
    public List<SiteFeedback> searchSiteFeedback(String locationId) {
        // Simulate external service call
        System.out.println("Calling external service for location: " + locationId);
        // Return dummy data
        return Arrays.asList(
                new SiteFeedback(locationId, "Good service", new Date()),
                new SiteFeedback(locationId, "Needs improvement", new Date())
        );
    }
}