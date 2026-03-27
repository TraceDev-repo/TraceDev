package com.agency.reporting.domain;

import java.util.Date;

/**
 * Represents feedback for a specific location.
 */
public class SiteFeedback {
    private final String locationId;
    private final String feedbackData;
    private final Date timestamp;

    public SiteFeedback(String locationId, String feedbackData, Date timestamp) {
        this.locationId = locationId;
        this.feedbackData = feedbackData;
        this.timestamp = timestamp;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getFeedbackData() {
        return feedbackData;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}