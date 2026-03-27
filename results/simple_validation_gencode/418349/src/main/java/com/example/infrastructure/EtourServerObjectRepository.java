package com.example.infrastructure;

import com.example.domain.CulturalObject;
import java.util.List;

/**
 * Infrastructure Layer: Handles ETOUR server connection with proactive health monitoring.
 */
public class EtourServerObjectRepository implements CulturalObjectRepository {
    private final EtourApiClient apiClient;
    private final int connectionTimeout;
    private final ConnectionMonitor connectionMonitor;

    public EtourServerObjectRepository(EtourApiClient apiClient, int connectionTimeout, ConnectionMonitor connectionMonitor) {
        this.apiClient = apiClient;
        this.connectionTimeout = connectionTimeout;
        this.connectionMonitor = connectionMonitor;
    }

    @Override
    public List<CulturalObject> searchByCriteria(SearchCriteria criteria) {
        // Simulate query execution against ETOUR server.
        // The sequence diagram shows raw data being transformed to domain objects.
        List<CulturalObject> rawData = apiClient.executeQuery(criteria);
        // In a real implementation, transformation logic would be here.
        return rawData;
    }

    @Override
    public List<CulturalObject> findByKeyword(String keyword) {
        // Simplified: delegate to searchByCriteria with only keyword.
        SearchCriteria criteria = new SearchCriteria(keyword, null, null, null);
        return searchByCriteria(criteria);
    }

    @Override
    public boolean hasData() {
        // For demo, assume there is data if connection is healthy.
        return checkConnection();
    }

    @Override
    public boolean checkConnection() {
        ConnectionStatus status = connectionMonitor.checkConnectionHealth();
        return status.isHealthy();
    }

    @Override
    public boolean checkDataAvailability() {
        // Delegate to hasData() for now, but could be more specific.
        return hasData();
    }

    /**
     * Added for proactive monitoring (as per class diagram).
     */
    public ConnectionStatus monitorConnectionHealth() {
        return connectionMonitor.checkConnectionHealth();
    }

    public ConnectionMonitor getConnectionMonitor() {
        return connectionMonitor;
    }
}