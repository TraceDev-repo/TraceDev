package com.agency.dto;

/**
 * Criteria for searching refreshment points.
 */
public class SearchCriteria {
    private String location;
    private String name;
    private String status;

    public SearchCriteria() {
    }

    public SearchCriteria(String location, String name, String status) {
        this.location = location;
        this.name = name;
        this.status = status;
    }

    public String buildQuery() {
        // Builds a query string based on criteria; simplified for demonstration
        StringBuilder query = new StringBuilder("SELECT * FROM refreshment_point WHERE 1=1");
        if (location != null && !location.isEmpty()) {
            query.append(" AND location LIKE '%").append(location).append("%'");
        }
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE '%").append(name).append("%'");
        }
        if (status != null && !status.isEmpty()) {
            query.append(" AND status = '").append(status).append("'");
        }
        return query.toString();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}