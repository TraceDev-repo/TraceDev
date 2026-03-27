package com.example.repository;

import com.example.database.ETOURDataSource;
import com.example.dto.SearchTouristDTO;
import com.example.entity.TouristAccount;
import com.example.enums.TouristAccountStatus;
import com.example.exception.ConnectionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Concrete Repository that interacts with the ETOUR server.
 * Assumption: ETOURDataSource provides a connection to the remote server.
 */
public class TouristAccountRepository implements ITouristAccountRepository {
    private ETOURDataSource dataSource;

    // Constructor
    public TouristAccountRepository(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TouristAccount> findByCriteria(SearchTouristDTO criteria) throws ConnectionException {
        // Simulate a connection attempt; if fails, throw ConnectionException.
        boolean connectionSuccessful = dataSource.isConnected();
        if (!connectionSuccessful) {
            throw new ConnectionException("CONN_001", "ETOUR connection failed");
        }

        // Simulate querying the ETOUR server and returning results.
        // In reality, this would execute a SQL query or call a web service.
        // For demonstration, we return a hard‑coded list of dummy accounts.
        List<TouristAccount> accounts = new ArrayList<>();

        // Add a few dummy accounts that partially match the criteria (if provided).
        if (criteria.getName() == null || criteria.getName().isEmpty() || "John".contains(criteria.getName())) {
            accounts.add(new TouristAccount("TA001", "John Doe", "john@example.com", "USA", new Date(), TouristAccountStatus.ACTIVE));
        }
        if (criteria.getEmail() == null || criteria.getEmail().isEmpty() || "jane@example.com".contains(criteria.getEmail())) {
            accounts.add(new TouristAccount("TA002", "Jane Smith", "jane@example.com", "Canada", new Date(), TouristAccountStatus.ACTIVE));
        }
        if (criteria.getCountry() == null || criteria.getCountry().isEmpty() || "UK".contains(criteria.getCountry())) {
            accounts.add(new TouristAccount("TA003", "Robert Brown", "robert@example.com", "UK", new Date(), TouristAccountStatus.INACTIVE));
        }

        return accounts;
    }
}