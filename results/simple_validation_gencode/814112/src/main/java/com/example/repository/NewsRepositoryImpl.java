
package com.example.repository;

import com.example.news.NewsEntity;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;

/**
 * JPA-based implementation of NewsRepository.
 */
public class NewsRepositoryImpl implements NewsRepository {

    private EntityManager entityManager;

    public NewsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<NewsEntity> findAll() {
        return entityManager.createQuery("SELECT n FROM NewsEntity n", NewsEntity.class)
                .getResultList();
    }

    @Override
    public Optional<NewsEntity> findById(String id) {
        NewsEntity entity = entityManager.find(NewsEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public NewsEntity save(NewsEntity entity, Long submittedVersion) {
        // Checks version conflict before proceeding with save.
        checkVersionConflict(entity, submittedVersion);
        // If no conflict, persist (merge for updates).
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public boolean existsById(String id) {
        Long count = entityManager.createQuery("SELECT COUNT(n) FROM NewsEntity n WHERE n.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    /**
     * Checks if the submitted version matches the current version in the database.
     * Added to satisfy requirement: data integrity during modification.
     * @param entity the entity with the submitted version.
     * @param submittedVersion the version sent by the client.
     * @throws OptimisticLockException if versions mismatch.
     */
    protected void checkVersionConflict(NewsEntity entity, Long submittedVersion) {
        if (entity.getId() != null) {
            NewsEntity current = entityManager.find(NewsEntity.class, entity.getId());
            if (current != null && !current.getVersion().equals(submittedVersion)) {
                throw new OptimisticLockException("Concurrent modification detected");
            }
        }
    }
}
