package com.example.application;

import com.example.domain.DataAccessException;
import com.example.domain.ETOURConnectionException;
import com.example.domain.RefreshmentPoint;
import com.example.infrastructure.ETOURServerDataSource;
import java.util.Map;

/**
 * Repository implementation that uses a data source to load data from server.
 */
public class RefreshmentPointRepository implements IRefreshmentPointRepository {
    private ETOURServerDataSource dataSource;

    public RefreshmentPointRepository(ETOURServerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RefreshmentPoint findById(Long id) {
        // This method could implement a fallback strategy (cache then server).
        // For simplicity, we delegate to loadDataFromServer.
        return loadDataFromServer(id);
    }

    @Override
    public RefreshmentPoint loadDataFromServer(Long id) {
        try {
            // Simulate loading from server; may throw connection exception.
            Map<String, Object> rawData = dataSource.loadRefreshmentPointData(id);
            return mapToEntity(rawData);
        } catch (RuntimeException e) {
            // Wrap any runtime exception as a connection exception
            throw new ETOURConnectionException("CONN_001", "Connection to server ETOUR is interrupted", e);
        }
    }

    private RefreshmentPoint mapToEntity(Map<String, Object> rawData) {
        RefreshmentPoint point = new RefreshmentPoint();
        point.setId(((Number) rawData.get("id")).longValue());
        point.setName((String) rawData.get("name"));
        point.setLocation((String) rawData.get("location"));
        point.setDescription((String) rawData.get("description"));
        point.setContactInfo((String) rawData.get("contactInfo"));
        return point;
    }
}