package com.example.ui;

import com.example.application.ViewRefreshmentPointDetailsService;
import com.example.domain.ServiceException;

/**
 * Boundary/Controller class for the view refreshment point details use case.
 */
public class ViewRefreshmentPointDetailsController {
    private ViewRefreshmentPointDetailsService service;

    public ViewRefreshmentPointDetailsController(ViewRefreshmentPointDetailsService service) {
        this.service = service;
    }

    /**
     * Handles the request to view details of a refreshment point.
     * @param refreshmentPointId the ID of the refreshment point
     * @return DTO with details
     */
    public RefreshmentPointDetailsDTO viewDetails(Long refreshmentPointId) throws ServiceException {
        try {
            return service.getRefreshmentPointDetails(refreshmentPointId);
        } catch (ServiceException e) {
            handleError(e);
            throw e; // re-throw after handling
        }
    }

    /**
     * Handles service exceptions and creates an error response DTO.
     * In a real application, this might log the error and return the error DTO.
     */
    public ErrorResponseDTO handleError(ServiceException exception) {
        ErrorResponseDTO errorDto = new ErrorResponseDTO(
            exception.getErrorCode() != null ? exception.getErrorCode() : "UNKNOWN",
            exception.getMessage()
        );
        // In a real UI, this error DTO would be used to display an error message.
        return errorDto;
    }
}