package com.example.web.form;

import com.example.web.dto.BannerDto;

/**
 * Represents a confirmation request sent to the user after validation passes.
 */
public class ConfirmationRequest {
    private String message;
    private BannerDto bannerData;

    public ConfirmationRequest() {
    }

    public ConfirmationRequest(String message, BannerDto bannerData) {
        this.message = message;
        this.bannerData = bannerData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BannerDto getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerDto bannerData) {
        this.bannerData = bannerData;
    }
}