
package com.example.news;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA entity representing a news article.
 */
@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    private String id;

    private String title;
    private String content;
    private String author;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Version
    private Long version; // Added for optimistic locking

    @Column(name = "last_modified_by")
    private String lastModifiedBy; // Added for audit trail

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate; // Added for audit trail

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
