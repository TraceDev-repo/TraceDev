package com.example.domain;

import java.util.Date;

/**
 * Represents the agreement data associated with a Convention.
 */
public class AgreementData {
    private boolean valid;
    private Date validationDate;
    private String terms;

    public AgreementData(String terms) {
        this.terms = terms;
        this.valid = false;
    }

    /**
     * Validates the agreement data.
     * Implements actual validation: terms must not be null or empty.
     */
    public boolean validate() {
        if (terms == null || terms.trim().isEmpty()) {
            this.valid = false;
            this.validationDate = new Date();
            return false;
        }
        this.valid = true;
        this.validationDate = new Date();
        return true;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}