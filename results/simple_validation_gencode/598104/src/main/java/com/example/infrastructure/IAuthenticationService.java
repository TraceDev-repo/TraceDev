package com.example.infrastructure;

import com.example.domain.AgencyOperator;

/**
 * Port interface for authentication service.
 */
public interface IAuthenticationService {
    AgencyOperator getCurrentOperator();
    boolean isAgencyOperatorLoggedIn();
}