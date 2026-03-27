package com.newsagency.repository;

import com.newsagency.model.News;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Concrete implementation of NewsRepository.
 * Uses an in-memory store for simplicity.
 */
public class NewsRepositoryImpl implements NewsRepository {

    private AtomicLong idGenerator = new AtomicLong(1L);

    @Override
    public News save(News news) {
        // Simulate setting an ID and creation timestamp (if not already set)
        if (news.getId() == null) {
            news.setId(idGenerator.getAndIncrement());
        }
        // In a real implementation, createdAt would be set by the entity or database.
        return news;
    }
}