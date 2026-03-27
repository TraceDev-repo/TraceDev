package com.example.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a request to activate a convention.
 */
public class ConventionRequest {
    private String requestId;
    private String conventionId;
    private Date activationDate;
    private RequestStatus status;
    private Map<String, Object> externalData;

    public ConventionRequest(String requestId, String conventionId) {
        this.requestId = requestId;
        this.conventionId = conventionId;
        this.status = RequestStatus.PENDING;
        this.externalData = new HashMap<>();
    }

    public String getId() {
        return requestId;
    }

    public void setId(String requestId) {
        this.requestId = requestId;
    }

    public String getConventionId() {
        return conventionId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return externalData;
    }

    /**
     * Updates external data fetched from point of rest.
     */
    public void updateExternalData(Map<String, Object> data) {
        this.externalData = data;
    }
}