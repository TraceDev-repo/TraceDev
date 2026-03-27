package com.example.repository;

import com.example.dto.ConventionDTO;
import com.example.server.ETourServer;
import com.example.exception.ConnectionException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Concrete implementation of the convention repository
public class ConventionRepositoryImpl implements IConventionRepository {
    private ETourServer eTourServer;

    // Constructor as specified in the class diagram
    public ConventionRepositoryImpl() {
        this.eTourServer = new ETourServer();
    }

    // Constructor with dependency injection for better testability
    public ConventionRepositoryImpl(ETourServer eTourServer) {
        this.eTourServer = eTourServer;
    }

    @Override
    public List<ConventionDTO> getHistoryByAgencyId(int agencyId) {
        // Check connection status first
        if (!eTourServer.isConnected()) {
            throw new ConnectionException("Server connection lost.");
        }

        Connection connection = null;
        try {
            connection = eTourServer.getConnection();

            // Simulate querying the ETOUR server for convention history
            // In a real implementation, this would execute a proper SQL query
            String query = "SELECT * FROM conventions WHERE agency_id = " + agencyId;

            // For demonstration, we'll simulate database interaction
            // and return sample data
            return simulateDatabaseQuery(agencyId);

        } catch (RuntimeException e) {
            // Re-throw connection or database exceptions as ConnectionException
            throw new ConnectionException("Server connection lost.", e);
        } finally {
            // In real implementation, close connection properly
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Log but don't throw - we already have the data or exception
                }
            }
        }
    }

    // Simulates database query and returns sample data
    private List<ConventionDTO> simulateDatabaseQuery(int agencyId) {
        List<ConventionDTO> conventions = new ArrayList<>();

        // Create sample convention data
        ConventionDTO dto1 = new ConventionDTO();
        dto1.setId(1);
        dto1.setAgencyId(agencyId);
        dto1.setDerivedFromPointOfRestId(100);
        dto1.setDateCreated(new Date(System.currentTimeMillis() - 86400000L)); // Yesterday

        ConventionDTO dto2 = new ConventionDTO();
        dto2.setId(2);
        dto2.setAgencyId(agencyId);
        dto2.setDerivedFromPointOfRestId(101);
        dto2.setDateCreated(new Date()); // Today

        conventions.add(dto1);
        conventions.add(dto2);

        return conventions;
    }
}