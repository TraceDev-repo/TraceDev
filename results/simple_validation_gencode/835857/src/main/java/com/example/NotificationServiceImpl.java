package com.example;

/**
 * Implementation of notification service.
 * Simulates sending notifications (e.g., email, logging).
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifySuccess(CulturalObject culturalObject) {
        // In a real system, this might send an email, SMS, or push notification.
        System.out.println("[Notification] Successfully created cultural object: " + culturalObject.getName());
    }

    @Override
    public String generateSuccessMessage(CulturalObject culturalObject) {
        return "Cultural object '" + culturalObject.getName() + "' (ID: " + culturalObject.getId() +
                ") of type '" + culturalObject.getType() + "' at location '" + culturalObject.getLocation() +
                "' has been registered successfully.";
    }
}