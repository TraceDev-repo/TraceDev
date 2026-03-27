package com.example.repository;

import com.example.dto.SearchTouristDTO;
import com.example.entity.TouristAccount;
import com.example.exception.ConnectionException;

import java.util.List;

/**
 * Repository interface that abstracts data access for tourist accounts.
 */
public interface ITouristAccountRepository {
    /**
     * Finds tourist accounts matching the given search criteria.
     * @param criteria the search criteria DTO
     * @return a list of matching TouristAccount entities
     * @throws ConnectionException if the connection to the ETOUR server fails
     */
    List<TouristAccount> findByCriteria(SearchTouristDTO criteria) throws ConnectionException;
}