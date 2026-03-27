package com.example;

/**
 * Represents an agency operator user.
 * Updated to depend on AuthenticationService instead of managing authentication state locally.
 */
public class AgencyOperator {
    private AuthenticationService authenticationService;

    public AgencyOperator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Checks if the operator is authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return authenticationService.isUserLoggedIn();
    }

    /**
     * Sets the authentication service (optional, for flexibility).
     * @param authenticationService the authentication service.
     */
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}