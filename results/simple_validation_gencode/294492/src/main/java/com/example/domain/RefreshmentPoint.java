package com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a refreshment point capable of displaying banners.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private int maxBanners;
    private List<Banner> banners = new ArrayList<>();

    public RefreshmentPoint() {
    }

    public RefreshmentPoint(String id, String name, int maxBanners) {
        this.id = id;
        this.name = name;
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

    public int getMaxBanners() {
        return maxBanners;
    }

    public void setMaxBanners(int maxBanners) {
        this.maxBanners = maxBanners;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    /**
     * Checks whether a new banner can be added to this refreshment point.
     */
    public boolean canAcceptNewBanner() {
        return banners.size() < maxBanners;
    }

    /**
     * Adds a banner to this refreshment point if capacity permits.
     * Maintains bidirectional relationship.
     */
    public void addBanner(Banner banner) {
        if (canAcceptNewBanner()) {
            banners.add(banner);
            banner.setRefreshmentPoint(this);
        } else {
            throw new IllegalStateException("Maximum banner limit reached.");
        }
    }

    /**
     * Returns the current number of banners associated with this point.
     * Added to satisfy requirement: System checks that the number of banners did not exceed the maximum.
     */
    public int getCurrentBannerCount() {
        return banners.size();
    }
}