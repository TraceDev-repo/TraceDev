package com.example.repository;

import com.example.news.NewsEntity;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for NewsEntity persistence operations.
 */
public interface NewsRepository {
    /**
     * Finds all news entities.
     * @return list of all news entities.
     */
    List<NewsEntity> findAll();

    /**
     * Finds a news entity by its ID.
     * @param id the entity ID.
     * @return Optional containing the entity if found.
     */
    Optional<NewsEntity> findById(String id);

    /**
     * Saves a news entity with optimistic locking.
     * Modified to satisfy requirement: data integrity during modification - explicit version parameter.
     * @param entity the entity to save.
     * @param submittedVersion the version submitted by the client.
     * @return the saved entity.
     */
    NewsEntity save(NewsEntity entity, Long submittedVersion);

    /**
     * Checks if an entity with the given ID exists.
     * Added to satisfy requirement: Flow of Events 3 - validation before loading.
     * @param id the ID to check.
     * @return true if exists.
     */
    boolean existsById(String id);
}