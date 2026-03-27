package com.etour.domain;

/**
 * Represents a banner displayed at a turning point.
 */
public class Banner {
    private String id;
    private String name;
    private String imageUrl;
    private String turningPointId;

    public Banner(String id, String name, String imageUrl, String turningPointId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.turningPointId = turningPointId;
    }

    /**
     * Updates the banner's image URL with a new one.
     * @param newUrl the new image URL
     */
    public void updateImage(String newUrl) {
        this.imageUrl = newUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTurningPointId() {
        return turningPointId;
    }
}