package com.example.domain.service;

/**
 * Interface for notification services.
 */
public interface INotificationService {
    void notifySuccess(String message);
    void notifyError(String message);
}