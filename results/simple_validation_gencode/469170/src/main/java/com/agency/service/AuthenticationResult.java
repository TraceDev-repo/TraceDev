package com.agency.service;

import com.agency.entity.AgencyOperator;

/**
 * Result of an authentication attempt.
 */
public class AuthenticationResult {
    private boolean success;
    private String message;
    private AgencyOperator operator;

    public AuthenticationResult(boolean success, String message, AgencyOperator operator) {
        this.success = success;
        this.message = message;
        this.operator = operator;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AgencyOperator getOperator() {
        return operator;
    }

    public void setOperator(AgencyOperator operator) {
        this.operator = operator;
    }
}