package com.newsagency.repository;

import com.newsagency.model.News;

/**
 * Repository interface for News persistence.
 */
public interface NewsRepository {
    News save(News news);
}