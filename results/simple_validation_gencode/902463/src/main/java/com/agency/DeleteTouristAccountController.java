package com.agency;

/**
 * Controller for deleting tourist accounts.
 * Handles authentication, confirmation, and deletion.
 */
public class DeleteTouristAccountController {
    private ITouristRepository touristRepository;
    private IConfirmationService confirmationService;
    private IAuthenticationService authService;
    private IConnectionManager connectionManager;
    private SearchTouristController searchTouristController;

    /**
     * Constructs a DeleteTouristAccountController.
     * @param repo the tourist repository.
     * @param confService the confirmation service.
     * @param authService the authentication service.
     */
    public DeleteTouristAccountController(ITouristRepository repo, IConfirmationService confService, IAuthenticationService authService) {
        this.touristRepository = repo;
        this.confirmationService = confService;
        this.authService = authService;
    }

    public DeleteTouristAccountController(ITouristRepository repo, IConfirmationService confService, IAuthenticationService authService, IConnectionManager connectionManager, SearchTouristController searchTouristController) {
        this.touristRepository = repo;
        this.confirmationService = confService;
        this.authService = authService;
        this.connectionManager = connectionManager;
        this.searchTouristController = searchTouristController;
    }

    /**
     * Main entry point for deleting a tourist account.
     * @param operator the agency operator performing the deletion.
     * @param touristId the ID of the tourist to delete.
     */
    public void execute(AgencyOperator operator, String touristId) {
        try {
            // Step 1: Validate authentication.
            if (!validateAuthentication(operator)) {
                System.out.println("Authentication failed. Aborting deletion.");
                return;
            }

            // Step 2: Explicit early connection validation as per sequence diagram
            validateConnectionEarly();

            // Step 1 (sequence diagram): Obtain list of tourists via SearchTouristController
            if (searchTouristController != null) {
                searchTouristController.searchTourists();
                searchTouristController.selectTourist(touristId);
            } else {
                // Fallback: use repository directly if search controller not set
                touristRepository.findAll(new SearchCriteria());
                touristRepository.findById(touristId);
            }

            // Step 3: Initiate deletion.
            initiateDeletion(operator, touristId);

            // Step 4: Confirm deletion.
            boolean confirmed = confirmDeletion(operator, touristId);
            if (!confirmed) {
                System.out.println("Deletion cancelled by operator.");
                return;
            }

            // Step 5: Connection check before deletion.
            validateConnectionBeforeDeletion();

            // Step 6: Perform deletion following sequence diagram order: findById then delete
            deleteTouristAccount(touristId);
            System.out.println("Tourist account deleted successfully.");
        } catch (TouristNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ConnectionException e) {
            System.out.println("Connection error during deletion: " + e.getMessage());
            System.out.println("Deletion failed due to connection loss. Tourist account preserved.");
        }
    }

    /**
     * Validates the operator's authentication.
     * @param operator the operator to validate.
     * @return true if authenticated, false otherwise.
     */
    public boolean validateAuthentication(AgencyOperator operator) {
        boolean isValid = authService.isSessionValid(operator);
        if (isValid) {
            System.out.println("Authentication verified for operator: " + operator.getName());
        } else {
            System.out.println("Authentication failed for operator: " + operator.getName());
        }
        return isValid;
    }

    /**
     * Explicit early connection validation as shown in sequence diagram.
     */
    private void validateConnectionEarly() {
        System.out.println("Performing early connection validation...");
        if (connectionManager != null) {
            if (!connectionManager.checkConnection()) {
                connectionManager.handleConnectionError();
                throw new ConnectionException("Early connection validation failed.");
            }
        } else {
            // Use repository's internal validation
            try {
                touristRepository.findAll(new SearchCriteria());
            } catch (ConnectionException e) {
                System.out.println("Early connection check failed: " + e.getMessage());
                System.out.println("Process terminated early.");
                throw e;
            }
        }
        System.out.println("Early connection validation passed.");
    }

    /**
     * Connection check before deletion as per sequence diagram.
     */
    private void validateConnectionBeforeDeletion() {
        System.out.println("Validating connection before deletion...");
        if (connectionManager != null) {
            if (!connectionManager.checkConnection()) {
                connectionManager.handleConnectionError();
                throw new ConnectionException("Connection lost before deletion.");
            }
        } else {
            try {
                touristRepository.findAll(new SearchCriteria());
            } catch (ConnectionException e) {
                System.out.println("Connection lost before deletion: " + e.getMessage());
                System.out.println("Deletion failed due to connection loss. Tourist account preserved.");
                throw e;
            }
        }
        System.out.println("Connection validated before deletion.");
    }

    /**
     * Initiates the deletion process.
     * @param operator the operator initiating deletion.
     * @param touristId the tourist ID.
     */
    public void initiateDeletion(AgencyOperator operator, String touristId) {
        System.out.println("Deletion initiated for tourist " + touristId + " by operator " + operator.getName());
        // Load tourist details and prepare deletion context as per suggestion
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new TouristNotFoundException("Tourist not found during initiation: " + touristId));
        System.out.println("Loaded tourist details: " + tourist);
        // Additional initialization logic can be added here
    }

    /**
     * Confirms deletion with the operator.
     * @param operator the operator to confirm with.
     * @param touristId the tourist ID.
     * @return true if confirmed, false otherwise.
     */
    public boolean confirmDeletion(AgencyOperator operator, String touristId) {
        return confirmationService.requestConfirmation(operator, "Delete tourist account " + touristId + "?");
    }

    /**
     * Deletes the tourist account following sequence diagram order.
     * @param touristId the ID of the tourist to delete.
     */
    public void deleteTouristAccount(String touristId) {
        // Step: findById call after confirmation (step 8 in sequence diagram)
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new TouristNotFoundException("Tourist not found for deletion: " + touristId));
        // Step: delete operation
        touristRepository.delete(tourist);
    }

    /**
     * Selects a tourist by ID.
     * @param touristId the tourist ID.
     * @return the selected tourist.
     * @throws TouristNotFoundException if tourist not found.
     */
    public Tourist selectTourist(String touristId) {
        return touristRepository.findById(touristId)
                .orElseThrow(() -> new TouristNotFoundException("Tourist not found: " + touristId));
    }

    public IConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public SearchTouristController getSearchTouristController() {
        return searchTouristController;
    }

    public void setSearchTouristController(SearchTouristController searchTouristController) {
        this.searchTouristController = searchTouristController;
    }
}