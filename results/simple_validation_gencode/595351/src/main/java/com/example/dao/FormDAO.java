package com.example.dao;

import com.example.domain.Form;

// Data Access Object for Form entities
public class FormDAO {
    
    // Retrieves a Form by agency ID
    public Form getFormByAgencyId(int agencyId) {
        // In a real implementation, this would query a database
        // For demonstration, return a form only for agency with ID 1
        
        if (agencyId == 1) {
            Form form = new Form();
            form.setId(1);
            form.setAgencyId(agencyId);
            form.setFormData("Sample form data for agency " + agencyId);
            return form;
        } else {
            return null; // No form found for this agency
        }
    }
}