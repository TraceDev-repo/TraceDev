package com.newsagency.service;

import com.newsagency.dto.NewsDTO;
import com.newsagency.model.News;
import com.newsagency.model.ValidationResult;
import com.newsagency.model.Result;
import com.newsagency.repository.NewsRepository;
import com.newsagency.util.TransactionManager;

/**
 * Application Service that orchestrates news insertion, including validation and storage.
 */
public class NewsService {

    private NewsValidator newsValidator;
    private NewsRepository newsRepository;
    private TransactionManager transactionManager;

    public NewsService(NewsValidator newsValidator,
                       NewsRepository newsRepository,
                       TransactionManager transactionManager) {
        this.newsValidator = newsValidator;
        this.newsRepository = newsRepository;
        this.transactionManager = transactionManager;
    }

    /**
     * Verifies news data and stores it if valid and confirmed.
     * This method corresponds to the main sequence.
     */
    public Result<News> verifyAndStoreNews(NewsDTO newsData) {
        // Validation first without transaction
        ValidationResult validationResult = newsValidator.validateNewsData(newsData);
        if (!validationResult.isValid()) {
            // No transaction to rollback because we haven't started one yet
            return new Result<>(false, null,
                    "Validation failed: " + String.join(", ", validationResult.getErrorMessages()));
        }
        // Simulate confirmation prompt (in real scenario, controller handles this)
        // For this method, we assume confirmation is handled outside, and we proceed to store.
        // Now begin transaction for storage
        transactionManager.beginTransaction();
        try {
            News news = new News(
                    validationResult.getValidatedData().getTitle(),
                    validationResult.getValidatedData().getContent(),
                    validationResult.getValidatedData().getAuthor()
            );
            News savedNews = newsRepository.save(news);
            transactionManager.commit();
            return new Result<>(true, savedNews, null);
        } catch (Exception e) {
            transactionManager.rollback();
            return new Result<>(false, null,
                    "Connection interrupted - News not saved: " + e.getMessage());
        }
    }

    /**
     * Stores validated news data after confirmation.
     */
    public Result<News> storeNews(NewsDTO validatedNewsData) {
        transactionManager.beginTransaction();
        try {
            // Convert DTO to entity
            News news = new News(
                    validatedNewsData.getTitle(),
                    validatedNewsData.getContent(),
                    validatedNewsData.getAuthor()
            );
            News savedNews = newsRepository.save(news);
            transactionManager.commit();
            return new Result<>(true, savedNews, null);
        } catch (Exception e) {
            transactionManager.rollback();
            // Simulate connection lost scenario
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("connection")) {
                return new Result<>(false, null,
                        "Database connection lost - Transaction rolled back.");
            }
            return new Result<>(false, null,
                    "Error - News not saved: " + e.getMessage());
        }
    }

    /**
     * Rolls back transaction if one is active.
     */
    public void rollbackIfInTransaction() {
        if (transactionManager.isInTransaction()) {
            transactionManager.rollback();
        }
    }

    /**
     * Get the NewsValidator instance.
     */
    public NewsValidator getNewsValidator() {
        return newsValidator;
    }
}