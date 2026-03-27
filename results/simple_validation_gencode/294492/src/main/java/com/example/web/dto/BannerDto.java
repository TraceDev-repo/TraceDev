package com.example.web.dto;

/**
 * DTO for transferring banner data between layers.
 */
public class BannerDto {
    private String refreshmentPointId;
    private byte[] imageData;
    private String imageFormat;

    public BannerDto() {
    }

    public BannerDto(String refreshmentPointId, byte[] imageData, String imageFormat) {
        this.refreshmentPointId = refreshmentPointId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }
}