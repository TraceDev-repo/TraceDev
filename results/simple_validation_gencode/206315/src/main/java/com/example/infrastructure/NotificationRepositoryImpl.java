package com.example.infrastructure;

import com.example.domain.Notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple in-memory implementation of NotificationRepository.
 */
public class NotificationRepositoryImpl implements NotificationRepository {
    private Map<String, Notification> storage = new HashMap<>();

    @Override
    public Notification save(Notification notification) {
        storage.put(notification.getId(), notification);
        return notification;
    }

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }
}