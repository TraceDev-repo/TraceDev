package com.example.validation;

import com.example.news.NewsDTO;

/**
 * Validates news data and version consistency.
 */
public class NewsValidator {

    /**
     * Validates the DTO fields.
     * @param dto the DTO to validate.
     * @return true if valid.
     */
    public boolean validate(NewsDTO dto) {
        return isTitleValid(dto.getTitle()) && isContentValid(dto.getContent());
    }

    /**
     * Validates the title.
     * @param title the title.
     * @return true if valid.
     */
    public boolean isTitleValid(String title) {
        return title != null && !title.trim().isEmpty() && title.length() <= 200;
    }

    /**
     * Validates the content.
     * @param content the content.
     * @return true if valid.
     */
    public boolean isContentValid(String content) {
        return content != null && !content.trim().isEmpty();
    }

    /**
     * Validates version consistency for optimistic locking.
     * @param currentVersion the current version in the database.
     * @param submittedVersion the version submitted by the client.
     * @return true if versions match.
     */
    public boolean isVersionValid(Long currentVersion, Long submittedVersion) {
        // In a real scenario, you might have more complex logic.
        return currentVersion != null && currentVersion.equals(submittedVersion);
    }
}