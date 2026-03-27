package com.example.domain;

/**
 * Represents an agency operator (user) of the system.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private String role;

    public AgencyOperator(String operatorId, String name, String role) {
        this.operatorId = operatorId;
        this.name = name;
        this.role = role;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean isAgencyOperator() {
        return "AGENCY_OPERATOR".equals(role);
    }
}