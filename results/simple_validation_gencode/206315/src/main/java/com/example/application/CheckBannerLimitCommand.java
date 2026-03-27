package com.example.application;

/**
 * Command object for the CheckBannerLimit use case.
 */
public class CheckBannerLimitCommand {
    private String conventionId;
    private String refreshmentPointId;

    public CheckBannerLimitCommand(String conventionId, String refreshmentPointId) {
        this.conventionId = conventionId;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }
}