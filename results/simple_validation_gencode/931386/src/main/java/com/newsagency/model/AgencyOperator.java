package com.newsagency.model;

import com.newsagency.controller.NewsController;
import com.newsagency.dto.NewsDTO;

/**
 * Represents an Agency Operator who interacts with the system.
 * This class is primarily an actor in the system and does not require
 * additional attributes or methods for our current implementation.
 */
public class AgencyOperator {

    private String userId;

    public AgencyOperator(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Initiates news insertion by interacting with NewsController.
     */
    public void insertNews(NewsController controller) {
        controller.activateNewsInsertion();
    }

    /**
     * Submits the filled news form.
     */
    public void submitNewsForm(NewsController controller, NewsDTO newsData) {
        controller.submitNewsForm(newsData);
    }

    /**
     * Cancels the ongoing operation.
     */
    public void cancelOperation(NewsController controller, String message) {
        controller.cancelOperation(message);
    }
}