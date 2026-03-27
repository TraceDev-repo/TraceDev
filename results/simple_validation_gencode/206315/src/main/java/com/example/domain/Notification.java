package com.example.domain;

/**
 * Notification domain model.
 */
public class Notification {
    private String id;
    private String message;
    private boolean confirmed;

    public Notification(String id, String message) {
        this.id = id;
        this.message = message;
        this.confirmed = false;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void confirm() {
        this.confirmed = true;
    }
}