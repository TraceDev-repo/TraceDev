package com.example.infrastructure;

/**
 * DTO used by repository to perform searches.
 */
public class SearchCriteria {
    private final String keyword;
    private final String objectType;
    private final String location;
    private final DateRange dateRange;

    public SearchCriteria(String keyword, String objectType, String location, DateRange dateRange) {
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
     * Validates the date range if present.
     */
    public boolean validateDateRange() {
        return dateRange == null || dateRange.isValid();
    }
}