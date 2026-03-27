package com.agency;

/**
 * Represents an agency operator who can perform operations on tourist accounts.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private IAuthenticationService authService;

    public AgencyOperator(String operatorId, String name, IAuthenticationService authService) {
        this.operatorId = operatorId;
        this.name = name;
        this.authService = authService;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IAuthenticationService getAuthService() {
        return authService;
    }

    public void setAuthService(IAuthenticationService authService) {
        this.authService = authService;
    }

    /**
     * Checks if the operator is authenticated.
     * This method delegates to the authentication service.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return authService.isSessionValid(this);
    }
}