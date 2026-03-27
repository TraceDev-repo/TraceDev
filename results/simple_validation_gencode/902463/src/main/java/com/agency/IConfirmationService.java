package com.agency;

/**
 * Interface for confirmation services.
 * Used to request user confirmation for critical operations.
 */
public interface IConfirmationService {
    /**
     * Requests confirmation from the operator.
     * @param operator the operator to confirm with.
     * @param message the confirmation message.
     * @return true if confirmed, false otherwise.
     */
    boolean requestConfirmation(AgencyOperator operator, String message);
}