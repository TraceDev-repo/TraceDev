package com.agency.reporting.infrastructure.persistence;

import com.agency.reporting.domain.SiteFeedback;
import java.util.List;

/**
 * Repository port for site feedback.
 */
public interface SiteFeedbackRepository {
    List<SiteFeedback> findByLocation(String locationId);
}