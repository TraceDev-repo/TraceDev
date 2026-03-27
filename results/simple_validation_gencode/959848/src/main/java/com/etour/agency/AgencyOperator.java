package com.etour.agency;

/**
 * Represents an Agency Operator who can manage banners.
 * Added to satisfy authentication/authorization requirements.
 */
public class AgencyOperator {
    private String id;
    private String name;
    private String email;

    public AgencyOperator(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}