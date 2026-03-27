
package com.example.infrastructure.persistence;

import com.example.application.ports.TouristRepository;
import com.example.domain.Tourist;
import com.example.infrastructure.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of TouristRepository using a DataSource.
 * Note: This is a simplified implementation for demonstration.
 * In a real application, proper SQL and exception handling would be used.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DataSource dataSource;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tourist> findAll() {
        // Simulating database query
        System.out.println("Executing: SELECT * FROM tourists");
        // In real implementation, would use dataSource.getConnection() and execute query
        // Implement proper exception handling
        List<Tourist> tourists = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tourists");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tourist tourist = new Tourist(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phoneNumber")
                );
                tourists.add(tourist);
            }
        } catch (SQLException e) {
            System.err.println("Connection error in findAll: " + e.getMessage());
            // Propagate exception as per sequence diagram alternative flow
            throw new RuntimeException("Database connection error", e);
        }
        return tourists;
    }

    @Override
    public Optional<Tourist> findById(String id) {
        // Simulating database query
        System.out.println("Executing: SELECT * FROM tourists WHERE id = " + id);
        // In real implementation, would use dataSource.getConnection() and execute query
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tourists WHERE id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tourist tourist = new Tourist(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phoneNumber")
                );
                return Optional.of(tourist);
            }
        } catch (SQLException e) {
            System.err.println("Connection error in findById: " + e.getMessage());
            // Propagate exception as per sequence diagram alternative flow
            throw new RuntimeException("Database connection error", e);
        }
        return Optional.empty();
    }

    @Override
    public Tourist save(Tourist tourist) {
        // Simulating database update/insert
        System.out.println("Executing: UPDATE tourists SET ... WHERE id = " + tourist.getId());
        // In real implementation, would use dataSource.getConnection() and execute query
        try (Connection conn = dataSource.getConnection()) {
            // Determine if insert or update based on existence
            Optional<Tourist> existing = findById(tourist.getId());
            if (existing.isPresent()) {
                PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tourists SET name=?, email=?, phoneNumber=? WHERE id=?"
                );
                stmt.setString(1, tourist.getName());
                stmt.setString(2, tourist.getEmail());
                stmt.setString(3, tourist.getPhoneNumber());
                stmt.setString(4, tourist.getId());
                stmt.executeUpdate();
            } else {
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tourists(id, name, email, phoneNumber) VALUES (?, ?, ?, ?)"
                );
                stmt.setString(1, tourist.getId());
                stmt.setString(2, tourist.getName());
                stmt.setString(3, tourist.getEmail());
                stmt.setString(4, tourist.getPhoneNumber());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Connection error in save: " + e.getMessage());
            // Propagate exception as per sequence diagram alternative flow
            throw new RuntimeException("Database connection error", e);
        }
        return tourist;
    }
}
