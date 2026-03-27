package com.example.presentation;

import com.example.domain.AgreementData;
import java.util.Map;

/**
 * Represents the activation form displayed to the agency operator.
 */
public class ActivationForm {
    private Map<String, Object> conventionData;
    private AgreementData agreementData;

    public ActivationForm(Map<String, Object> conventionData, AgreementData agreementData) {
        this.conventionData = conventionData;
        this.agreementData = agreementData;
    }

    public void display() {
        System.out.println("Displaying activation form with convention data and agreement.");
    }

    public boolean getConfirmation() {
        // In a real UI, this would wait for user input.
        // For simulation, assume user confirms.
        return true;
    }
}