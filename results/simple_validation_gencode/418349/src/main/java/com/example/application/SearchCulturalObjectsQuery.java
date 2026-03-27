package com.example.application;

import com.example.infrastructure.DateRange;

/**
 * DTO representing a search query from the UI.
 */
public class SearchCulturalObjectsQuery {
    private final String keyword;
    private final String objectType;
    private final String location;
    private final DateRange dateRange;

    public SearchCulturalObjectsQuery(String keyword, String objectType, String location, DateRange dateRange) {
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
     * Validates the query parameters.
     * At least one search criterion must be provided.
     */
    public boolean isValid() {
        return (keyword != null && !keyword.trim().isEmpty()) ||
               (objectType != null && !objectType.trim().isEmpty()) ||
               (location != null && !location.trim().isEmpty()) ||
               (dateRange != null && dateRange.isValid());
    }
}