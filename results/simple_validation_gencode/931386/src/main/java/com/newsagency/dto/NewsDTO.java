package com.newsagency.dto;

/**
 * Data Transfer Object for News, used in UI and service layers.
 */
public class NewsDTO {

    private String title;
    private String content;
    private String author;
    private boolean confirmationRequired;

    public NewsDTO() {
    }

    public NewsDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.confirmationRequired = true; // default to requiring confirmation
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isConfirmationRequired() {
        return confirmationRequired;
    }

    public void setConfirmationRequired(boolean confirmationRequired) {
        this.confirmationRequired = confirmationRequired;
    }
}