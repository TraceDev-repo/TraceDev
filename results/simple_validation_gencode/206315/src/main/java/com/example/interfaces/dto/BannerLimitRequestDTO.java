package com.example.interfaces.dto;

/**
 * DTO for banner limit request.
 */
public class BannerLimitRequestDTO {
    private String conventionId;
    private String refreshmentPointId;

    public String getConventionId() {
        return conventionId;
    }

    public void setConventionId(String conventionId) {
        this.conventionId = conventionId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }
}