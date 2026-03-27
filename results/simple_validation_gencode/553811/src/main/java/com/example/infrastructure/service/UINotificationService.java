package com.example.infrastructure.service;

import com.example.domain.service.INotificationService;

/**
 * Concrete implementation of INotificationService that outputs to console.
 */
public class UINotificationService implements INotificationService {
    @Override
    public void notifySuccess(String message) {
        System.out.println("[NOTIFICATION SUCCESS] " + message);
    }

    @Override
    public void notifyError(String message) {
        System.out.println("[NOTIFICATION ERROR] " + message);
    }
}