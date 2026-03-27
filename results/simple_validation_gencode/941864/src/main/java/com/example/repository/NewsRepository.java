package com.example.repository;

import com.example.model.News;
import com.example.exception.ETourConnectionException;
import java.util.List;

/**
 * Repository interface for News data access.
 * Methods as per class diagram.
 */
public interface NewsRepository {
    List<News> findAll() throws ETourConnectionException;
    void delete(String id) throws ETourConnectionException;
}