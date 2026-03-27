package com.example.model;

import java.util.Date;

/**
 * Core business entity representing a News item.
 * Attributes and methods as per the class diagram.
 */
public class News {
    private String id;
    private String title;
    private String content;
    private Date publishedDate;

    public News(String id, String title, String content, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String toString() {
        // For display purposes in the UI
        return "[" + id + "] " + title + " (Published: " + publishedDate + ")";
    }
}