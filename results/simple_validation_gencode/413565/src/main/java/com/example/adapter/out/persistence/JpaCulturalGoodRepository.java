package com.example.adapter.out.persistence;

import com.example.application.port.out.CulturalGoodRepository;
import com.example.domain.CulturalGood;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation of the CulturalGoodRepository.
 */
public class JpaCulturalGoodRepository implements CulturalGoodRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CulturalGood findById(String id) {
        return entityManager.find(CulturalGood.class, id);
    }

    @Override
    public CulturalGood save(CulturalGood culturalGood) {
        if (culturalGood.getId() == null) {
            entityManager.persist(culturalGood);
            return culturalGood;
        } else {
            return entityManager.merge(culturalGood);
        }
    }
}