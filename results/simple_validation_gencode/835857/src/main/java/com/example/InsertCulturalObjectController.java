package com.example;

/**
 * Controller for inserting a new cultural object.
 * Acts as the entry point for the use case.
 */
public class InsertCulturalObjectController {
    private CulturalObjectService culturalObjectService;
    private AuthenticationService authenticationService;
    private CulturalObjectMapper mapper;
    private CulturalObjectValidator validator;
    private boolean cancellationRequested = false;

    public InsertCulturalObjectController(CulturalObjectService culturalObjectService,
                                          AuthenticationService authenticationService,
                                          CulturalObjectValidator validator) {
        this.culturalObjectService = culturalObjectService;
        this.authenticationService = authenticationService;
        this.validator = validator;
        this.mapper = new CulturalObjectMapper();
    }

    /**
     * Shows the insert form. Verifies authentication first.
     * @return the form to be displayed.
     * @throws SecurityException if user is not logged in.
     */
    public CulturalObjectForm showInsertForm() {
        // Check authentication before form display
        if (!authenticationService.isUserLoggedIn()) {
            throw new SecurityException("User must be logged in to access this feature.");
        }
        // Simulate cancellation check during form display
        if (cancellationRequested) {
            notifyOperationCancelled();
            throw new IllegalStateException("Operation cancelled by user.");
        }
        // In a real scenario, we might load existing data; here we return a new empty form.
        return mapper.toForm(null); // null for empty form
    }

    /**
     * Submits the filled form to create a cultural object.
     * @param formData the form data filled by the user.
     * @return the response containing the created object details.
     * @throws SecurityException if user is not logged in.
     * @throws ValidationException if validation fails.
     * @throws RuntimeException for other errors (e.g., connection loss).
     */
    public CulturalObjectResponse submitForm(CulturalObjectForm formData) {
        // Convert form to request (as per sequence diagram order)
        CreateCulturalObjectRequest request = mapper.fromForm(formData);

        // Authentication check after conversion (reordered to match sequence diagram)
        if (!authenticationService.isUserLoggedIn()) {
            handleConnectionLost();
            throw new SecurityException("User must be logged in to perform this action.");
        }

        // Validate the request before confirmation
        try {
            validator.validateForCreation(request);
        } catch (ValidationException e) {
            // Handle validation failure (could notify UI)
            throw e;
        }

        // Ask for transaction confirmation after validation
        askForTransactionConfirmation();
        // In a real scenario, we would wait for user input.
        // For simplicity, we assume confirmation is given; if not, cancelTransaction() would be called.
        confirmTransaction();

        // Delegate to service layer
        CulturalObject created = culturalObjectService.createCulturalObject(request);

        // Map to response and return
        CulturalObjectResponse response = mapper.toResponse(created);
        // Notification details are set by the service layer via the mapper
        return response;
    }

    /**
     * Separated validation method (optional, for clarity).
     */
    public void validateForm(CulturalObjectForm formData) {
        CreateCulturalObjectRequest request = mapper.fromForm(formData);
        validator.validateForCreation(request);
    }

    /**
     * Simulates asking the user for transaction confirmation.
     * In a real application, this would trigger a UI modal.
     */
    public void askForTransactionConfirmation() {
        System.out.println("Please confirm the transaction (yes/no)");
    }

    /**
     * Handles user confirmation.
     * This method is called after the user confirms the transaction.
     * The actual creation is done in submitForm; this is for sequence flow.
     */
    public void confirmTransaction() {
        System.out.println("Transaction confirmed by user.");
    }

    /**
     * Handles user cancellation after submission.
     */
    public void cancelTransaction() {
        System.out.println("Transaction cancelled by user after submission.");
        cancellationRequested = true;
    }

    /**
     * Notifies the user that the operation was cancelled.
     */
    public void notifyOperationCancelled() {
        System.out.println("Operation cancelled.");
    }

    /**
     * Notifies the user of successful inclusion.
     * @param response the response containing success details.
     */
    public void notifyProperInclusion(CulturalObjectResponse response) {
        System.out.println("Cultural object successfully inserted: " + response.getMessage());
        System.out.println("Notification details: " + response.getNotificationDetails());
    }

    /**
     * Handles connection loss exception.
     */
    public void handleConnectionLost() {
        System.out.println("Connection lost. Operation aborted.");
        // In a real scenario, would throw a custom exception or trigger recovery.
        throw new RuntimeException("Connection lost.");
    }

    /**
     * Sets a cancellation flag (for testing).
     */
    public void requestCancellation() {
        cancellationRequested = true;
    }
}