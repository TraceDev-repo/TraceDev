package com.example.infrastructure;

import com.example.domain.TouristAccount;

/**
 * Port interface for notification service.
 */
public interface INotificationService {
    void notifyStatusChange(TouristAccount account);
}