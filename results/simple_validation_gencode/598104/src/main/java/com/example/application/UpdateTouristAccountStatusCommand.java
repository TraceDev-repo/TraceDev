package com.example.application;

import com.example.domain.AccountStatus;

/**
 * Command object for updating tourist account status.
 */
public class UpdateTouristAccountStatusCommand {
    private String touristAccountId;
    private AccountStatus targetStatus;

    public UpdateTouristAccountStatusCommand(String accountId, AccountStatus targetStatus) {
        this.touristAccountId = accountId;
        this.targetStatus = targetStatus;
    }

    public String getTouristAccountId() {
        return touristAccountId;
    }

    public AccountStatus getTargetStatus() {
        return targetStatus;
    }
}