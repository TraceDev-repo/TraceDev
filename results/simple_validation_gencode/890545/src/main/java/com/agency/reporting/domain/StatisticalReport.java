package com.agency.reporting.domain;

import java.util.List;
import java.util.Map;

/**
 * Contains statistical report data for a location.
 */
public class StatisticalReport {
    private final Location location;
    private final List<SiteFeedback> feedbackList;
    private final Map<String, Double> calculations;

    public StatisticalReport(Location location, List<SiteFeedback> feedbackList, Map<String, Double> calculations) {
        this.location = location;
        this.feedbackList = feedbackList;
        this.calculations = calculations;
    }

    public Location getLocation() {
        return location;
    }

    public List<SiteFeedback> getFeedbackList() {
        return feedbackList;
    }

    public Map<String, Double> getCalculations() {
        return calculations;
    }

    public String generateReportData() {
        // Simplified report generation
        return String.format("Report for %s: %d feedback items, %d metrics",
                location.getName(), feedbackList.size(), calculations.size());
    }
}