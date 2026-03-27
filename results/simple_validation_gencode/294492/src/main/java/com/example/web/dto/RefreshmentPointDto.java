package com.example.web.dto;

/**
 * DTO for transferring refreshment point information to the UI.
 */
public class RefreshmentPointDto {
    private String id;
    private String name;
    private int currentBannerCount;
    private int maxBanners;

    public RefreshmentPointDto() {
    }

    public RefreshmentPointDto(String id, String name, int currentBannerCount, int maxBanners) {
        this.id = id;
        this.name = name;
        this.currentBannerCount = currentBannerCount;
        this.maxBanners = maxBanners;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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