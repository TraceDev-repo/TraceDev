package com.newsagency.model;

import java.util.Date;

/**
 * Entity representing a News item.
 */
public class News {

    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private String author;

    public News(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = new Date(); // Set creation timestamp on instantiation
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}