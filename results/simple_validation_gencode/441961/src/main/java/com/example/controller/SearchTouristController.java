package com.example.controller;

import com.example.dto.SearchTouristDTO;
import com.example.dto.TouristAccountDTO;
import com.example.entity.TouristAccount;
import com.example.exception.ConnectionException;
import com.example.repository.ITouristAccountRepository;
import com.example.service.IAuthenticationService;
import com.example.service.SessionManager;
import com.example.validation.SearchValidator;
import com.example.validation.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Case Controller that orchestrates the search flow.
 */
public class SearchTouristController {
    private ITouristAccountRepository touristAccountRepository;
    private IAuthenticationService authenticationService;
    private SessionManager sessionManager;
    private SearchValidator validator;

    // Constructor with dependency injection
    public SearchTouristController(ITouristAccountRepository touristAccountRepository,
                                   IAuthenticationService authenticationService,
                                   SessionManager sessionManager,
                                   SearchValidator validator) {
        this.touristAccountRepository = touristAccountRepository;
        this.authenticationService = authenticationService;
        this.sessionManager = sessionManager;
        this.validator = validator;
    }

    /**
     * Executes the tourist search according to the sequence diagram.
     * @param criteria the search criteria provided by the UI
     * @return a list of TouristAccountDTOs for presentation
     */
    public List<TouristAccountDTO> executeSearch(SearchTouristDTO criteria) throws ConnectionException {
        // Step 1: Check session and authenticate (as per sequence diagram)
        String sessionId = sessionManager.getCurrentSessionId();
        boolean authenticated = authenticationService.verifyAgencyOperatorSession(sessionId);
        if (!authenticated) {
            throw new SecurityException("Access denied: Invalid session.");
        }

        // Step 2: Validate search criteria
        ValidationResult validationResult = validator.validateSearchCriteria(criteria);
        if (!validationResult.getIsValid()) {
            // Instead of throwing IllegalArgumentException, we could return error DTO,
            // but we keep existing pattern and let UI handle via submitSearch method
            throw new IllegalArgumentException("Validation failed: " + validationResult.getErrorMessages());
        }

        // Step 3: Query repository
        List<TouristAccount> accounts = touristAccountRepository.findByCriteria(criteria);

        // Step 4: Handle empty result (optional exit condition)
        if (accounts.isEmpty()) {
            return new ArrayList<>(); // empty list signals no results
        }

        // Step 5: Map entities to DTOs
        List<TouristAccountDTO> resultDTOs = new ArrayList<>();
        for (TouristAccount account : accounts) {
            TouristAccountDTO dto = new TouristAccountDTO();
            dto.setAccountId(account.getAccountId());
            dto.setFullName(account.getFullName());
            dto.setEmail(account.getEmail());
            dto.setNationality(account.getNationality());
            dto.setRegistrationDate(account.getRegistrationDate());
            resultDTOs.add(dto);
        }

        return resultDTOs;
    }

    /**
     * Alternative method that returns ValidationResult instead of throwing exception.
     * This better aligns with sequence diagram showing validationResult returned.
     * @param criteria the search criteria
     * @return ValidationResult containing validation outcome
     */
    public ValidationResult validateSearch(SearchTouristDTO criteria) {
        return validator.validateSearchCriteria(criteria);
    }
}