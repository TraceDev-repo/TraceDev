package com.example.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * The main Convention entity containing business logic for activation.
 */
public class Convention {
    private String id;
    private String refreshmentPointId;
    private ConventionStatus status;
    private Map<String, Object> data;
    private AgreementData agreementData;
    private Agency agency;

    public Convention(String id, String refreshmentPointId) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.status = ConventionStatus.PENDING;
        this.data = new HashMap<>();
    }

    /**
     * Activates the convention by changing its status to ACTIVE.
     */
    public void activate() {
        this.status = ConventionStatus.ACTIVE;
    }

    /**
     * Validates the convention's data. For simplicity, always returns true.
     */
    public boolean validateData() {
        // Check required data fields
        return !data.isEmpty();
    }

    /**
     * Loads agreement data. In a real system, this might fetch from a repository.
     */
    public AgreementData loadAgreementData() {
        if (agreementData == null) {
            agreementData = new AgreementData("Standard terms and conditions.");
        }
        return agreementData;
    }

    /**
     * Validates the agreement. Delegates to AgreementData.
     */
    public boolean validateAgreement() {
        if (agreementData == null) {
            loadAgreementData();
        }
        return agreementData.validate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public ConventionStatus getStatus() {
        return status;
    }

    public void setStatus(ConventionStatus status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public AgreementData getAgreementData() {
        return agreementData;
    }

    public void setAgreementData(AgreementData agreementData) {
        this.agreementData = agreementData;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}