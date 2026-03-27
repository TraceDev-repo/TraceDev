package com.example.adapter.in.web;

import com.example.application.port.in.EditCulturalGoodUseCase;
import com.example.domain.CulturalGood;
import com.example.adapter.presentation.CulturalGoodForm;
import com.example.adapter.confirmation.ConfirmationHandler;
import com.example.application.service.EditCulturalGoodService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Web controller handling HTTP requests for cultural goods.
 */
public class CulturalGoodController {
    private EditCulturalGoodUseCase editCulturalGoodUseCase;
    private EditCulturalGoodService editCulturalGoodService;
    private boolean isSubmitting = false;

    public CulturalGoodController(EditCulturalGoodUseCase useCase) {
        this.editCulturalGoodUseCase = useCase;
        if (useCase instanceof EditCulturalGoodService) {
            this.editCulturalGoodService = (EditCulturalGoodService) useCase;
        }
    }

    public List<CulturalGood> displayList() {
        // In a real implementation, this would call a repository method like findAll().
        // For simplicity, we return an empty list.
        return new ArrayList<>();
    }

    public CulturalGood selectCulturalGood(String id) {
        return editCulturalGoodUseCase.loadCulturalGood(id);
    }

    public void activateChange(String id) {
        // Activation step: could prepare context, log, etc.
        System.out.println("Change activated for id: " + id);
    }

    public CulturalGoodForm showEditForm(CulturalGood culturalGood) {
        return new CulturalGoodForm(culturalGood);
    }

    public String submitEditForm(String id, Map<String, Object> formData) {
        if (isSubmitting) {
            return "Please wait, submission in progress";
        }
        isSubmitting = true;
        try {
            // Step 1: Create and validate form data
            CulturalGoodForm form = new CulturalGoodForm();
            form.fromMap(formData);
            if (!form.validate()) {
                return "Invalid form data";
            }
            // Step 2: Acquire lock before server connection check (as per sequence diagram)
            if (editCulturalGoodService != null) {
                boolean lockAcquired = editCulturalGoodService.acquireLockForUpdate();
                if (!lockAcquired) {
                    return "Resource is locked, please try later";
                }
            } else {
                // fallback: simulate lock acquisition
                System.out.println("Lock acquisition simulated.");
            }
            // Step 3: Create confirmation handler and request confirmation
            ConfirmationHandler confHandler = new ConfirmationHandler("update", form.getName());
            boolean confirmed = confHandler.requestConfirmation();
            if (!confirmed) {
                if (editCulturalGoodService != null) {
                    editCulturalGoodService.releaseLockForUpdate();
                }
                return "Operation cancelled";
            }
            // Step 4: Update cultural good (lock already acquired)
            boolean success = editCulturalGoodUseCase.updateCulturalGood(id, formData);
            if (editCulturalGoodService != null) {
                editCulturalGoodService.releaseLockForUpdate();
            }
            if (success) {
                return "Update successful";
            } else {
                return "Update failed (connection or validation error)";
            }
        } finally {
            isSubmitting = false;
        }
    }

    public boolean confirmOperation() {
        // This would be called when user confirms via UI.
        return true;
    }

    public void cancelOperation() {
        // Cancel ongoing operation.
        if (editCulturalGoodService != null) {
            editCulturalGoodService.releaseLockForUpdate();
        }
        isSubmitting = false;
    }
}