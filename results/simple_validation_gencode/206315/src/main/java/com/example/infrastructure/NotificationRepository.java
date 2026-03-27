package com.example.infrastructure;

import com.example.domain.Notification;

import java.util.Optional;

/**
 * Repository interface for Notification entities.
 */
public interface NotificationRepository {
    Notification save(Notification notification);
    Optional<Notification> findById(String id);
}