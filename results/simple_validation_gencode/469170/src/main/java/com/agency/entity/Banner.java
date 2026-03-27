package com.agency.entity;

import java.util.Date;

/**
 * Represents an advertisement banner associated with a refreshment point.
 */
public class Banner {
    private String id;
    private String refreshmentPointId;
    private String contentUrl;
    private Date startDate;
    private Date endDate;

    public Banner() {
    }

    public Banner(String id, String refreshmentPointId, String contentUrl, Date startDate, Date endDate) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.contentUrl = contentUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isActive() {
        Date now = new Date();
        return now.after(startDate) && now.before(endDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}