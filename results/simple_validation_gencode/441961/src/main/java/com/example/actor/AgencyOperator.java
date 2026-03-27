package com.example.actor;

/**
 * Actor class representing the Agency Operator.
 * In a real system, the actor would be external (human or system),
 * but we include this class for completeness and clarity.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private String email;
    private String agencyId;

    // Constructors
    public AgencyOperator() {}

    public AgencyOperator(String operatorId, String name, String email, String agencyId) {
        this.operatorId = operatorId;
        this.name = name;
        this.email = email;
        this.agencyId = agencyId;
    }

    // Getters and Setters
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}