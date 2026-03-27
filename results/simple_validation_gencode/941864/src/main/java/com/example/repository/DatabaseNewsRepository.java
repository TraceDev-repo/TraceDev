package com.example.repository;

import com.example.model.News;
import com.example.exception.ETourConnectionException;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Concrete repository implementation that uses a DataSource.
 * Simulates database operations; may throw ETourConnectionException.
 */
public class DatabaseNewsRepository implements NewsRepository {
    private DataSource dataSource;

    public DatabaseNewsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<News> findAll() throws ETourConnectionException {
        // Simulate database call; in real scenario would use dataSource
        // For demonstration, return a hardcoded list.
        List<News> newsList = new ArrayList<>();
        newsList.add(new News("1", "News One", "Content of news one.", new Date()));
        newsList.add(new News("2", "News Two", "Content of news two.", new Date()));
        newsList.add(new News("3", "News Three", "Content of news three.", new Date()));
        // Simulate potential connection interruption (randomly for demonstration)
        if (Math.random() < 0.1) {
            throw new ETourConnectionException("Connection to server interrupted while fetching news.");
        }
        return newsList;
    }

    @Override
    public void delete(String id) throws ETourConnectionException {
        // Simulate deletion in database
        // Simulate potential connection interruption (randomly for demonstration)
        if (Math.random() < 0.1) {
            throw new ETourConnectionException("Connection to server interrupted while deleting news.");
        }
        // In a real implementation, would execute SQL delete using dataSource
        System.out.println("News with id " + id + " deleted from database.");
    }
}