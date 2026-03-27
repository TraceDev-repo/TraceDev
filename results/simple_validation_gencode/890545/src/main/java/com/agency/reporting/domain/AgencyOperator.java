package com.agency.reporting.domain;

/**
 * Represents an operator belonging to an agency.
 */
public class AgencyOperator {
    private final String id;
    private final String name;
    private final String agencyId;

    public AgencyOperator(String id, String name, String agencyId) {
        this.id = id;
        this.name = name;
        this.agencyId = agencyId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAgencyId() {
        return agencyId;
    }
}