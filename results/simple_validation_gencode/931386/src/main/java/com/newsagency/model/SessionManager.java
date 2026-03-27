package com.newsagency.model;

import java.util.Date;

/**
 * Entity representing a user session.
 * Managed by AuthService.
 */
public class SessionManager {

    private String sessionId;
    private String userId;
    private Date expiresAt;

    public SessionManager(String sessionId, String userId, Date expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public boolean isValid() {
        return expiresAt != null && expiresAt.after(new Date());
    }

    public void renew() {
        // Renew session by extending expiration time (e.g., by 30 minutes)
        long renewalMillis = 30 * 60 * 1000;
        expiresAt = new Date(System.currentTimeMillis() + renewalMillis);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}