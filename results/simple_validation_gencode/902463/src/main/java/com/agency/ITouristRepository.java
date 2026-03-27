package com.agency;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Tourist entities.
 */
public interface ITouristRepository {
    /**
     * Finds all tourists matching the given criteria.
     * @param criteria the search criteria.
     * @return list of matching tourists.
     */
    List<Tourist> findAll(SearchCriteria criteria);

    /**
     * Finds a tourist by ID.
     * @param id the tourist ID.
     * @return an Optional containing the tourist if found.
     */
    Optional<Tourist> findById(String id);

    /**
     * Deletes a tourist.
     * @param tourist the tourist to delete.
     */
    void delete(Tourist tourist);

    /**
     * Saves a tourist.
     * @param tourist the tourist to save.
     * @return the saved tourist.
     */
    Tourist save(Tourist tourist);
}