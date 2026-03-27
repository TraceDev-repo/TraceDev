package com.example.infrastructure.persistence;

import com.example.application.ports.NotificationRepository;
import com.example.domain.NotificationEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter implementation for NotificationRepository.
 */
public class NotificationRepositoryImpl implements NotificationRepository {
    private List<NotificationEvent> events = new ArrayList<>();

    @Override
    public void save(NotificationEvent event) {
        events.add(event);
        System.out.println("Saved notification event: " + event.getId());
    }

    @Override
    public List<NotificationEvent> findPendingNotifications() {
        List<NotificationEvent> pending = new ArrayList<>();
        for (NotificationEvent event : events) {
            if (event.getStatus().equals(com.example.domain.NotificationStatus.PENDING)) {
                pending.add(event);
            }
        }
        return pending;
    }

    @Override
    public void update(NotificationEvent event) {
        // In a real implementation, update the existing event.
        System.out.println("Updated notification event: " + event.getId());
    }
}