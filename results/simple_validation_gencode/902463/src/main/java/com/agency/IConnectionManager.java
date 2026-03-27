package com.agency;

/**
 * Interface for managing connections to external servers.
 */
public interface IConnectionManager {
    /**
     * Checks the connection to the server.
     * @return true if connection is available, false otherwise.
     */
    boolean checkConnection();

    /**
     * Handles connection errors.
     */
    void handleConnectionError();

    /**
     * Checks if the server is available.
     * @return true if server is available, false otherwise.
     */
    boolean isServerAvailable();
}