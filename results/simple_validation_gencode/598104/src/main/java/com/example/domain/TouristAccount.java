package com.example.domain;

/**
 * Represents a tourist account in the system.
 * Composed of TouristDetails and has an AccountStatus.
 */
public class TouristAccount {
    private String accountId;
    private AccountStatus status;
    private TouristDetails touristDetails;

    public TouristAccount(String accountId, TouristDetails details) {
        this.accountId = accountId;
        this.touristDetails = details;
        this.status = AccountStatus.INACTIVE; // default status
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public TouristDetails getTouristDetails() {
        return touristDetails;
    }

    public void enable() {
        this.status = AccountStatus.ACTIVE;
    }

    public void disable() {
        this.status = AccountStatus.INACTIVE;
    }

    public boolean isActive() {
        return status == AccountStatus.ACTIVE;
    }
}