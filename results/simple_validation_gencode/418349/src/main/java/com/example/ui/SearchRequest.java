package com.example.ui;

import com.example.infrastructure.DateRange;

/**
 * DTO for UI search request.
 */
public class SearchRequest {
    private final String keyword;
    private final String objectType;
    private final String location;
    private final DateRange dateRange;

    public SearchRequest(String keyword, String objectType, String location, DateRange dateRange) {
        this.keyword = keyword;
        this.objectType = objectType;
        this.location = location;
        this.dateRange = dateRange;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getLocation() {
        return location;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    /**
     * Validates that at least one field is provided.
     */
    public boolean isValid() {
        return (keyword != null && !keyword.trim().isEmpty()) ||
               (objectType != null && !objectType.trim().isEmpty()) ||
               (location != null && !location.trim().isEmpty()) ||
               (dateRange != null && dateRange.isValid());
    }

    /**
     * Validates date range if present.
     */
    public boolean validateDateRange() {
        return dateRange == null || dateRange.isValid();
    }
}