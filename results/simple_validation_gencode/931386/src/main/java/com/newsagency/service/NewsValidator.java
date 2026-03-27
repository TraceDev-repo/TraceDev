package com.newsagency.service;

import com.newsagency.dto.NewsDTO;
import com.newsagency.model.ValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that validates news data.
 */
public class NewsValidator {

    /**
     * Validates the provided NewsDTO.
     */
    public ValidationResult validateNewsData(NewsDTO newsData) {
        List<String> errors = new ArrayList<>();
        boolean valid = true;

        if (!validateTitle(newsData.getTitle())) {
            errors.add("Title is invalid (must be 1-200 characters).");
            valid = false;
        }
        if (!validateContent(newsData.getContent())) {
            errors.add("Content is invalid (must not be empty).");
            valid = false;
        }
        if (!validateAuthor(newsData.getAuthor())) {
            errors.add("Author is invalid (must be 1-100 characters).");
            valid = false;
        }

        return new ValidationResult(valid, errors, newsData);
    }

    public boolean validateTitle(String title) {
        return title != null && title.length() >= 1 && title.length() <= 200;
    }

    public boolean validateContent(String content) {
        return content != null && !content.trim().isEmpty();
    }

    public boolean validateAuthor(String author) {
        return author != null && author.length() >= 1 && author.length() <= 100;
    }
}