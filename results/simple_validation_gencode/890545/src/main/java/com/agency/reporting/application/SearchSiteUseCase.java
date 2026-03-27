package com.agency.reporting.application;

import com.agency.reporting.domain.SiteFeedback;
import java.util.List;

/**
 * Use case interface for searching site feedback.
 */
public interface SearchSiteUseCase {
    List<SiteFeedback> searchSiteFeedback(String locationId);
}