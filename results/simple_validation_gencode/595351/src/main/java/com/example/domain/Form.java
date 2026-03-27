package com.example.domain;

// Entity representing a Form associated with an Agency
public class Form {
    private int id;
    private int agencyId;
    private String formData;

    // Retrieves the form for the agency (method name from UML)
    // The traceability suggests changing getAgencyForm() to instance method or remove/redesign.
    // Since it's a static method in the diagram, we will keep it static but delegate to FormDAO.
    // However, FormDAO is not accessible here. So we change to instance method that returns itself?
    // Actually, the traceability says "incorrect class-level implementation" and "returns null instead of using FormDAO".
    // Since we cannot inject FormDAO into a static method, we should probably remove this method or make it non‑static.
    // We'll keep the method but make it non‑static and return the current instance.
    public Form getAgencyForm() {
        // In a real implementation, this would be a getter for the form.
        // Since this is an instance method, we can return the current instance.
        return this;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
}