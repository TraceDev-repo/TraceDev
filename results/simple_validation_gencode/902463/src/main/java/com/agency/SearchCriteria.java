package com.agency;

/**
 * Criteria for searching tourists.
 */
public class SearchCriteria {
    private String nameFilter;
    private String emailFilter;
    private boolean activeOnly;

    public SearchCriteria() {
        this.nameFilter = "";
        this.emailFilter = "";
        this.activeOnly = false;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String name) {
        this.nameFilter = name;
    }

    public String getEmailFilter() {
        return emailFilter;
    }

    public void setEmailFilter(String email) {
        this.emailFilter = email;
    }

    public boolean isActiveOnly() {
        return activeOnly;
    }

    public void setActiveOnly(boolean activeOnly) {
        this.activeOnly = activeOnly;
    }
}