package com.example;

/**
 * Service for sending notifications.
 */
public interface NotificationService {
    /**
     * Sends a success notification for a created cultural object.
     * @param culturalObject the created object.
     */
    void notifySuccess(CulturalObject culturalObject);

    /**
     * Generates a success message for the created object.
     * @param culturalObject the created object.
     * @return the formatted message.
     */
    String generateSuccessMessage(CulturalObject culturalObject);
}