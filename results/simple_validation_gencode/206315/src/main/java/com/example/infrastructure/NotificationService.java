package com.example.infrastructure;

import com.example.domain.Notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing notifications.
 */
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public String sendNotification(String message) {
        String id = UUID.randomUUID().toString();
        Notification notification = new Notification(id, message);
        notificationRepository.save(notification);
        return id;
    }

    public void confirmNotification(String notificationId) {
        Optional<Notification> notificationOpt = notificationRepository.findById(notificationId);
        notificationOpt.ifPresent(Notification::confirm);
    }
}