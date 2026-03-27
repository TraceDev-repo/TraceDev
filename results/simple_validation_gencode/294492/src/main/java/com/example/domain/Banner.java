package com.example.domain;

import java.util.Date;

/**
 * Entity representing a banner that can be displayed at a refreshment point.
 */
public class Banner {
    private String id;
    private String imageFilePath;
    private long imageSize;
    private String imageFormat;
    private Date creationDate;
    private RefreshmentPoint refreshmentPoint;

    public Banner() {
    }

    public Banner(String id, String imageFilePath, long imageSize, String imageFormat, Date creationDate) {
        this.id = id;
        this.imageFilePath = imageFilePath;
        this.imageSize = imageSize;
        this.imageFormat = imageFormat;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RefreshmentPoint getRefreshmentPoint() {
        return refreshmentPoint;
    }

    public void setRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        this.refreshmentPoint = refreshmentPoint;
    }

    /**
     * Placeholder for image validation logic (e.g., size, format, content).
     * In a real scenario this would check image characteristics.
     */
    public boolean validateImage() {
        // For simplicity, assume validation passes if size > 0 and format is non‑empty.
        return imageSize > 0 && imageFormat != null && !imageFormat.trim().isEmpty();
    }
}