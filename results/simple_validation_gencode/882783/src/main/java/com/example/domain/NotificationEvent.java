package com.example.domain;

import java.util.Date;

/**
 * Represents a notification event for tracking notification delivery.
 */
public class NotificationEvent {
    private String id;
    private String conventionId;
    private NotificationStatus status;
    private int retryCount;
    private Date lastAttempt;

    public NotificationEvent(String id, String conventionId) {
        this.id = id;
        this.conventionId = conventionId;
        this.status = NotificationStatus.PENDING;
        this.retryCount = 0;
        this.lastAttempt = new Date();
    }

    /**
     * Marks the notification as sent.
     */
    public void markAsSent() {
        this.status = NotificationStatus.SENT;
        this.lastAttempt = new Date();
    }

    /**
     * Marks the notification as failed and increments retry count.
     */
    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
        this.retryCount++;
        this.lastAttempt = new Date();
    }

    /**
     * Determines if the notification should be retried.
     * For simplicity, retry if retryCount < 3.
     */
    public boolean shouldRetry() {
        return status == NotificationStatus.FAILED && retryCount < 3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConventionId() {
        return conventionId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Date getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(Date lastAttempt) {
        this.lastAttempt = lastAttempt;
    }
}