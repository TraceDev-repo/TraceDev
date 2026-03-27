package com.agency.dto;

import java.util.Date;

/**
 * Result of a banner deletion operation.
 */
public class DeleteBannerResult {
    private boolean success;
    private String message;
    private String deletedBannerId;
    private Date timestamp;

    public DeleteBannerResult() {
    }

    public DeleteBannerResult(boolean success, String message, String deletedBannerId) {
        this.success = success;
        this.message = message;
        this.deletedBannerId = deletedBannerId;
        this.timestamp = new Date();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeletedBannerId() {
        return deletedBannerId;
    }

    public void setDeletedBannerId(String deletedBannerId) {
        this.deletedBannerId = deletedBannerId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}