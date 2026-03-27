package com.example.web.form;

/**
 * UI form for banner insertion, pre‑filled with refreshment point data.
 */
public class BannerInsertionForm {
    private String refreshmentPointId;
    private String refreshmentPointName;
    private int currentBannerCount;
    private int maxBanners;

    public BannerInsertionForm() {
    }

    public BannerInsertionForm(String refreshmentPointId, String refreshmentPointName, int currentBannerCount, int maxBanners) {
        this.refreshmentPointId = refreshmentPointId;
        this.refreshmentPointName = refreshmentPointName;
        this.currentBannerCount = currentBannerCount;
        this.maxBanners = maxBanners;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getRefreshmentPointName() {
        return refreshmentPointName;
    }

    public void setRefreshmentPointName(String refreshmentPointName) {
        this.refreshmentPointName = refreshmentPointName;
    }

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    public void setCurrentBannerCount(int currentBannerCount) {
        this.currentBannerCount = currentBannerCount;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public void setMaxBanners(int maxBanners) {
        this.maxBanners = maxBanners;
    }
}