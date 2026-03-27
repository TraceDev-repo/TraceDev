package com.agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Tourist entities with database connectivity.
 * Uses a DataSource and a ConnectionManager to interact with the ETOUR server.
 */
public class TouristRepository implements ITouristRepository {
    private DataSource dataSource;
    private IConnectionManager connectionManager;

    public TouristRepository(DataSource dataSource, IConnectionManager connMgr) {
        this.dataSource = dataSource;
        this.connectionManager = connMgr;
    }

    @Override
    public List<Tourist> findAll(SearchCriteria criteria) {
        validateConnection();
        // Simulate database query with criteria.
        // In a real scenario, this would execute a SQL query via dataSource.
        System.out.println("Finding tourists with criteria: nameFilter=" + criteria.getNameFilter() +
                ", emailFilter=" + criteria.getEmailFilter() + ", activeOnly=" + criteria.isActiveOnly());
        // Return a dummy list for demonstration.
        List<Tourist> dummyList = new ArrayList<>();
        dummyList.add(new Tourist("T001", "John Doe", "john@example.com"));
        dummyList.add(new Tourist("T002", "Jane Smith", "jane@example.com"));
        return dummyList;
    }

    @Override
    public Optional<Tourist> findById(String id) {
        validateConnection();
        // Simulate database lookup.
        System.out.println("Looking up tourist with ID: " + id);
        // Return a dummy tourist for demonstration.
        if ("T001".equals(id)) {
            return Optional.of(new Tourist("T001", "John Doe", "john@example.com"));
        } else if ("T002".equals(id)) {
            return Optional.of(new Tourist("T002", "Jane Smith", "jane@example.com"));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Tourist tourist) {
        validateConnection();
        // Simulate delete operation on the ETOUR server.
        System.out.println("Deleting tourist: " + tourist.getTouristId());
        // In a real scenario, this would perform a DELETE SQL command.
    }

    @Override
    public Tourist save(Tourist tourist) {
        validateConnection();
        // Simulate save/update operation.
        System.out.println("Saving tourist: " + tourist.getTouristId());
        // In a real scenario, this would perform an INSERT or UPDATE.
        return tourist;
    }

    /**
     * Validates the connection to the ETOUR server.
     * Throws a ConnectionException if connection fails.
     */
    void validateConnection() {
        if (!connectionManager.checkConnection()) {
            connectionManager.handleConnectionError();
            throw new ConnectionException("Connection to ETOUR server failed.");
        }
        System.out.println("Connection to ETOUR server validated.");
    }
}