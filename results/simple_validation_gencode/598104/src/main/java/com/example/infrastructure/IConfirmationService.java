package com.example.infrastructure;

import com.example.domain.AccountStatus;

/**
 * Port interface for confirmation service.
 */
public interface IConfirmationService {
    boolean requestConfirmation(String accountId, AccountStatus newStatus);
}