package com.example.controller;

import com.example.model.News;
import com.example.repository.NewsRepository;
import com.example.exception.ETourConnectionException;
import java.util.List;

/**
 * Use Case Controller that orchestrates the news deletion flow.
 * Contains methods as per class and sequence diagrams.
 */
public class DeleteNewsController {
    private NewsRepository newsRepository;

    public DeleteNewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Fetches all news from repository.
     * Called by NewsDeletionForm in sequence diagram.
     * @return List of News
     * @throws ETourConnectionException if connection interrupted
     */
    public List<News> displayAllNews() throws ETourConnectionException {
        // Quality requirement: Complete within 2 seconds (simulated by fast operation)
        return newsRepository.findAll();
    }

    /**
     * Simulates confirmation request. In a real UI, this would involve a dialog.
     * As per sequence diagram: controller returns true if confirmed, false if cancelled.
     * @param selectedNewsId the ID of selected news
     * @return true if confirmed, false if cancelled
     */
    public boolean requestConfirmation(String selectedNewsId) {
        // Implement confirmation logic that validates the selected news exists.
        // For demonstration, we'll check if the ID is not empty.
        if (selectedNewsId == null || selectedNewsId.trim().isEmpty()) {
            return false;
        }
        // In reality, we might fetch the news to verify existence.
        // For now, assume confirmation is required.
        return true;
    }

    /**
     * Executes deletion of the selected news.
     * Called by NewsDeletionForm after confirmation.
     * @param selectedNewsId the ID of the news to delete
     * @throws ETourConnectionException if connection interrupted
     */
    public void executeDeletion(String selectedNewsId) throws ETourConnectionException {
        newsRepository.delete(selectedNewsId);
    }
}