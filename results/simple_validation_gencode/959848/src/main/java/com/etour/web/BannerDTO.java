package com.etour.web;

/**
 * Data Transfer Object for Banner, used in the presentation layer.
 */
public class BannerDTO {
    private String id;
    private String name;
    private String currentImageUrl;
    
    public BannerDTO() {}
    
    public BannerDTO(String id, String name, String currentImageUrl) {
        this.id = id;
        this.name = name;
        this.currentImageUrl = currentImageUrl;
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
    
    public String getCurrentImageUrl() {
        return currentImageUrl;
    }
    
    public void setCurrentImageUrl(String url) {
        this.currentImageUrl = url;
    }
}