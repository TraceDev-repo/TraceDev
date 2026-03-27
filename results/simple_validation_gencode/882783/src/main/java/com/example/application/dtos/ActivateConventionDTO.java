package com.example.application.dtos;

/**
 * Data Transfer Object for activating a convention.
 */
public class ActivateConventionDTO {
    private String conventionId;
    private String operatorId;
    private String refreshmentPointId;

    public ActivateConventionDTO(String conventionId, String operatorId, String refreshmentPointId) {
        this.conventionId = conventionId;
        this.operatorId = operatorId;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getConventionId() {
        return conventionId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }
}