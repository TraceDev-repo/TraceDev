package com.agency;

/**
 * Interface for authentication services.
 * Provides methods to validate credentials and session.
 */
public interface IAuthenticationService {
    /**
     * Validates the credentials of an operator.
     * @param operator the operator to validate.
     * @return true if credentials are valid, false otherwise.
     */
    boolean validateCredentials(AgencyOperator operator);

    /**
     * Checks if the session for an operator is valid.
     * @param operator the operator whose session is checked.
     * @return true if session is valid, false otherwise.
     */
    boolean isSessionValid(AgencyOperator operator);
}