package repositories;

/**
 * Custom exception to simulate connection interruption or data access errors.
 * As per sequence diagram, REPO_IMPL throws DataAccessException when connection fails.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}