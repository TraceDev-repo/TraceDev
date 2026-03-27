package dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Data Transfer Object for Feedback as per the class diagram.
 * Contains feedbackId, content, and timestamp.
 */
public class FeedbackDTO {
    private String feedbackId;
    private String content;
    private LocalDateTime timestamp;

    /**
     * Constructor for FeedbackDTO.
     * @param feedbackId the feedback identifier
     * @param content the feedback content
     * @param timestamp the time when feedback was created
     */
    public FeedbackDTO(String feedbackId, String content, LocalDateTime timestamp) {
        this.feedbackId = feedbackId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getFeedbackId() {
        return feedbackId;
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
        FeedbackDTO that = (FeedbackDTO) o;
        return Objects.equals(feedbackId, that.feedbackId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(feedbackId);
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "feedbackId='" + feedbackId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}