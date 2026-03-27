package com.example.application.ports;

import com.example.domain.NotificationEvent;
import java.util.List;

/**
 * Port (Interface) for NotificationEvent repository.
 */
public interface NotificationRepository {
    void save(NotificationEvent event);
    List<NotificationEvent> findPendingNotifications();
    void update(NotificationEvent event);
}