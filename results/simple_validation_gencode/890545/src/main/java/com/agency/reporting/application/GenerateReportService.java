package com.agency.reporting.application;

import com.agency.reporting.domain.Location;
import com.agency.reporting.domain.SiteFeedback;
import com.agency.reporting.domain.StatisticalReport;
import com.agency.reporting.infrastructure.persistence.SiteFeedbackRepository;
import com.agency.reporting.domain.ReportCalculator;
import java.util.List;
import java.util.Map;

/**
 * Service implementing the GenerateReportUseCase.
 */
public class GenerateReportService implements GenerateReportUseCase {
    private final SiteFeedbackRepository siteFeedbackRepository;
    private final ReportCalculator reportCalculator;

    public GenerateReportService(SiteFeedbackRepository siteFeedbackRepository, ReportCalculator reportCalculator) {
        this.siteFeedbackRepository = siteFeedbackRepository;
        this.reportCalculator = reportCalculator;
    }

    @Override
    public StatisticalReport execute(String locationId) {
        // Step 1: Fetch feedback for location
        List<SiteFeedback> feedbackList = siteFeedbackRepository.findByLocation(locationId);
        if (feedbackList == null || feedbackList.isEmpty()) {
            throw new IllegalStateException("No feedback found for location: " + locationId);
        }

        // Step 2: Aggregate feedback data (simplified)
        // In a real scenario, this could involve filtering, grouping, etc.
        aggregateFeedbackData(feedbackList);

        // Step 3: Calculate statistics
        Map<String, Double> calculations = reportCalculator.calculate(feedbackList);

        // Step 4: Create report object (assuming location is constructed from id/name)
        // In a full implementation, we would fetch the Location object from a repository
        Location location = new Location(locationId, "Location " + locationId);
        return new StatisticalReport(location, feedbackList, calculations);
    }

    private void aggregateFeedbackData(List<SiteFeedback> feedbackList) {
        // Placeholder for aggregation logic
        // For example, could combine feedback data or perform initial transformations
        System.out.println("Aggregating " + feedbackList.size() + " feedback items");
    }
}