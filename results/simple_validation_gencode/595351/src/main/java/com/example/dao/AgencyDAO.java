package com.example.dao;

import com.example.domain.Agency;
import com.example.domain.Form;

// Data Access Object for Agency entities
public class AgencyDAO {

    // Retrieves an Agency by its ID
    public Agency getById(int id) {
        // In a real implementation, this would query a database
        // For demonstration, return a sample agency
        // Now we need to also set the Form association (1-1 mandatory)
        Form form = new Form();
        form.setId(id);
        form.setAgencyId(id);
        form.setFormData("Sample form data for agency " + id);
        if (id == 1) {
            return new Agency(id, "Sample Agency", true, form); // Designated point of rest
        } else if (id == 2) {
            return new Agency(id, "Other Agency", false, form); // Not designated point of rest
        } else {
            return null; // Not found
        }
    }
}