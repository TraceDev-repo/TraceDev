package com.newsagency.controller;

import com.newsagency.service.AuthService;
import com.newsagency.service.NewsService;
import com.newsagency.dto.NewsDTO;
import com.newsagency.model.ValidationResult;
import com.newsagency.model.Result;
import com.newsagency.model.News;
import com.newsagency.util.TransactionManager;
import java.util.Date;

/**
 * Boundary class that handles user interactions for inserting news.
 */
public class NewsController {

    private AuthService authService;
    private NewsService newsService;
    private TransactionManager transactionManager;

    public NewsController(AuthService authService, NewsService newsService) {
        this.authService = authService;
        this.newsService = newsService;
        // Obtain TransactionManager from NewsService via getter if needed
        // For now, we will rely on NewsService for transaction coordination.
    }

    /**
     * Initiates the news insertion flow.
     * Called by the AgencyOperator.
     */
    public void activateNewsInsertion() {
        // Add authentication check as per sequence diagram
        if (!authService.checkAuthentication()) {
            authService.displayLoginRequired();
            return;
        }
        displayNewsForm();
    }

    public void displayNewsForm() {
        System.out.println("Displaying news entry form...");
    }

    /**
     * Submits the filled news form.
     * Called by AgencyOperator with a NewsDTO.
     */
    public void submitNewsForm(NewsDTO newsData) {
        // Authentication guard before proceeding
        if (!authService.checkAuthentication()) {
            authService.displayLoginRequired();
            return;
        }
        // First validate data
        ValidationResult validationResult = newsService.getNewsValidator().validateNewsData(newsData);
        if (!validationResult.isValid()) {
            displayErrorNotification("Validation failed: " + String.join(", ", validationResult.getErrorMessages()));
            return;
        }
        // Ask for confirmation
        boolean confirmed = displayConfirmationPrompt(validationResult.getValidatedData());
        if (!confirmed) {
            cancelOperation("User cancelled at confirmation prompt.");
            return;
        }
        // Proceed with storage after successful validation and confirmation
        Result<News> result = newsService.storeNews(validationResult.getValidatedData());
        if (result.isSuccess()) {
            displaySuccessNotification(result.getData().getId(), result.getData().getCreatedAt());
        } else {
            displayErrorNotification(result.getErrorMessage());
        }
    }

    /**
     * Displays a confirmation prompt with validated data.
     * Returns true if user confirms, false otherwise.
     * In our sequence, user confirmation triggers confirmOperation().
     */
    public boolean displayConfirmationPrompt(NewsDTO validatedData) {
        System.out.println("Please confirm the following news data:");
        System.out.println("Title: " + validatedData.getTitle());
        System.out.println("Author: " + validatedData.getAuthor());
        System.out.println("Content preview: " +
                (validatedData.getContent().length() > 50 ?
                        validatedData.getContent().substring(0, 50) + "..." :
                        validatedData.getContent()));
        // Simulate interactive user input - for demonstration assume true (confirmed).
        // In a real system, this would wait for user response.
        return true;
    }

    public void displaySuccessNotification(Long newsId, Date timestamp) {
        System.out.println("Success! News inserted with ID: " + newsId + " at " + timestamp);
    }

    public void displayErrorNotification(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Handles cancellation at any point in the flow.
     */
    public void cancelOperation(String message) {
        // Coordinate with NewsService and TransactionManager for rollback
        // Ensure any active transaction is rolled back
        newsService.rollbackIfInTransaction();
        displayCancellationMessage(message);
    }

    public void displayCancellationMessage(String message) {
        System.out.println("Cancelled: " + message);
    }
}