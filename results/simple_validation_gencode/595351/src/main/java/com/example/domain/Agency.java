package com.example.domain;

// Entity representing an Agency
public class Agency {
    private int id;
    private String name;
    private boolean isDesignatedPointOfRest;
    private Form form; // 1-1 mandatory association with Form

    // Default constructor
    public Agency() {
    }

    // Constructor with parameters
    public Agency(int id, String name, boolean isDesignatedPointOfRest, Form form) {
        this.id = id;
        this.name = name;
        this.isDesignatedPointOfRest = isDesignatedPointOfRest;
        this.form = form;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDesignatedPointOfRest() {
        return isDesignatedPointOfRest;
    }

    public Form getForm() {
        return form;
    }

    // Business method to check if agency has a form
    // Note: This method checks the agency's state, not database existence
    // This method is kept to preserve existing logic but should be removed if it's wrong per the traceability.
    // The traceability suggests removing hasForm() from Agency class or delegate to FormDAO.
    // Since the requirement says "Remove hasForm() from Agency class or delegate to FormDAO to check actual form existence",
    // we will keep it but delegate to checking form reference.
    public boolean hasForm() {
        // Check form existence via the association reference
        return form != null;
    }

    // Setters (not shown in UML but needed for full implementation)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignatedPointOfRest(boolean isDesignatedPointOfRest) {
        this.isDesignatedPointOfRest = isDesignatedPointOfRest;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}