package com.agency.entity;

/**
 * Represents an agency operator with authentication and authorization status.
 */
public class AgencyOperator {
    private boolean authenticated;
    private boolean authorized;
    private String id;
    private String name;

    public AgencyOperator() {
    }

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean login(Credentials credentials) {
        // Simplistic login simulation; real implementation would validate credentials
        this.authenticated = (credentials != null);
        return authenticated;
    }

    public boolean hasPermission(String permission) {
        // Simplified permission check; real logic would verify against user roles
        this.authorized = "MANAGE_BANNERS".equals(permission);
        return authorized;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}