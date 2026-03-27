package com.example;

/**
 * Main class to demonstrate the flow.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        CulturalObjectRepository repository = new CulturalObjectRepositoryImpl();
        CulturalObjectMapper mapper = new CulturalObjectMapper();
        NotificationService notificationService = new NotificationServiceImpl();
        CulturalObjectValidator validator = new CulturalObjectValidator(repository);
        CulturalObjectService service = new CulturalObjectServiceImpl(validator, repository, mapper, notificationService);
        AuthenticationService authService = new AuthenticationServiceImpl();
        InsertCulturalObjectController controller = new InsertCulturalObjectController(service, authService, validator);
        AgencyOperator operator = new AgencyOperator(authService);

        // Simulate login (Entry condition: Agency Operator IS logged)
        User user = new User("john_doe", "AGENCY_OPERATOR");
        ((AuthenticationServiceImpl) authService).login(user);
        System.out.println("User logged in: " + user.getUsername());

        // Step 1: Activate Insert New Cultural Object Feature (via UI)
        System.out.println("\n1. Agency Operator activates Insert New Cultural Object Feature.");

        // Step 2: Show Insert Form (UI calls controller)
        try {
            CulturalObjectForm form = controller.showInsertForm();
            System.out.println("2. Form displayed with empty fields.");

            // Simulate early cancellation path (optional)
            // If we wanted to cancel: controller.requestCancellation();
            // Then the next call to showInsertForm would throw.

            // Step 3: Fill Form and Submit (simulate form data)
            form.setName("Ancient Temple");
            form.setType("MONUMENT");
            form.setLocation("Athens, Greece");
            form.setDescription("A historical temple from 5th century BC.");
            System.out.println("3. Form filled and submitted.");

            // Step 4: Submit form (UI calls controller)
            // Step 5: System verifies data (inside service)
            // Step 6: Ask for transaction confirmation (now inside submitForm)
            // Step 7: Process transaction
            CulturalObjectResponse response = controller.submitForm(form);

            // Step 8: Notify proper inclusion
            controller.notifyProperInclusion(response);

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Authentication error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Cancellation: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        // Simulate duplicate scenario
        System.out.println("\n--- Testing duplicate prevention ---");
        CulturalObjectForm duplicateForm = new CulturalObjectForm();
        duplicateForm.setName("Ancient Temple");
        duplicateForm.setType("MONUMENT");
        duplicateForm.setLocation("Athens, Greece");
        duplicateForm.setDescription("Another description.");
        try {
            controller.submitForm(duplicateForm);
        } catch (ValidationException e) {
            System.out.println("Duplicate correctly rejected: " + e.getMessage());
        }

        System.out.println("\n--- Testing cancellation after submission ---");
        controller.cancelTransaction();
        controller.notifyOperationCancelled();

        // Simulate connection loss (optional)
        System.out.println("\n--- Simulating connection loss ---");
        try {
            controller.handleConnectionLost();
        } catch (RuntimeException e) {
            System.out.println("Connection loss handled: " + e.getMessage());
        }

        // Test operator authentication via AuthenticationService
        System.out.println("\n--- Testing AgencyOperator authentication ---");
        System.out.println("Operator is authenticated: " + operator.isAuthenticated());
        ((AuthenticationServiceImpl) authService).logout();
        System.out.println("After logout, operator is authenticated: " + operator.isAuthenticated());
    }
}