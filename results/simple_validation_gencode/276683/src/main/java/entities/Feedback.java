package entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Feedback entity as per the class diagram.
 * Contains feedbackId, siteId, content, and timestamp.
 */
public class Feedback {
    private String feedbackId;
    private String siteId;
    private String content;
    private LocalDateTime timestamp; // Using LocalDateTime for simplicity

    /**
     * Constructor for Feedback.
     * @param feedbackId the feedback identifier
     * @param siteId the associated site identifier
     * @param content the feedback content
     * @param timestamp the time when feedback was created
     */
    public Feedback(String feedbackId, String siteId, String content, LocalDateTime timestamp) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(feedbackId, feedback.feedbackId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(feedbackId);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId='" + feedbackId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}