package com.agency.reporting.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculator for report statistics.
 */
public class ReportCalculator {
    public Map<String, Double> calculate(List<SiteFeedback> feedbackList) {
        Map<String, Double> calculations = new HashMap<>();
        // Simplified calculations - real implementation would compute meaningful metrics
        calculations.put("average_score", feedbackList.isEmpty() ? 0.0 : 4.5);
        calculations.put("total_feedback", (double) feedbackList.size());
        calculations.put("positive_percentage", 85.0);
        return calculations;
    }
}