
package com.example.presentation;

import com.example.application.dtos.ActivateConventionDTO;
import com.example.application.dtos.ActivationResult;
import com.example.application.usecases.ActivateConventionUseCase;
import com.example.domain.AgreementData;
import com.example.domain.Convention;
import com.example.domain.ConventionRequest;

import java.util.Map;

/**
 * Controller handling convention activation requests.
 */
public class ConventionController {
    private ActivateConventionUseCase activateConventionUseCase;

    public ConventionController(ActivateConventionUseCase activateConventionUseCase) {
        this.activateConventionUseCase = activateConventionUseCase;
    }

    public ActivationForm showActivationForm(String requestId) {
        System.out.println("Showing activation form for request: " + requestId);
        // Step 2: Load data from point of rest via Agency
        com.example.domain.ConventionRequest request = activateConventionUseCase.fetchConventionRequestFromPointOfRest(requestId);
        if (request == null) {
            return new ActivationForm(null, null);
        }
        // Load convention and agreement data
        Convention convention = new Convention(requestId, "RP-" + requestId);
        AgreementData agreementData = convention.loadAgreementData();
        return new ActivationForm(request.getData(), agreementData);
    }

    public ActivationResult activateConvention(ActivateConventionDTO dto) {
        // Step 0: Validate agency designation before proceeding
        if (!activateConventionUseCase.validateAgencyDesignation(dto.getOperatorId())) {
            return new ActivationResult(false, "Agency is not designated for activation", false);
        }

        // Step 1: Show activation form (data loading integrated)
        ActivationForm form = showActivationForm(dto.getConventionId());
        form.display();

        // Step 4: Optional validation check
        if (!activateConventionUseCase.validateAgreementData(dto.getConventionId())) {
            return new ActivationResult(false, "Agreement validation failed", false);
        }

        // Step 5: Agency Operator decides for activation (separate cognitive step)
        decideToActivate();

        // Step 6: Ask for confirmation
        ConfirmationDialog dialog = requestActivationConfirmation();
        boolean confirmed = dialog.show();
        if (!confirmed) {
            return new ActivationResult(false, "Operation cancelled", false);
        }

        // Step 7: Execute activation use case
        return activateConventionUseCase.execute(dto);
    }

    public ConfirmationDialog requestActivationConfirmation() {
        return new ConfirmationDialog("Please confirm activation.");
    }

    public void decideToActivate() {
        System.out.println("Decision to activate captured.");
    }

    // Inner class for ConventionRequest (if not imported)
    static class ConventionRequest {
        private String requestId;
        private String conventionId;
        private Map<String, Object> externalData;

        public ConventionRequest(String requestId, String conventionId) {
            this.requestId = requestId;
            this.conventionId = conventionId;
            this.externalData = new java.util.HashMap<>();
        }

        public String getId() {
            return requestId;
        }

        public Map<String, Object> getData() {
            return externalData;
        }

        public void updateExternalData(Map<String, Object> data) {
            this.externalData = data;
        }
    }
}
