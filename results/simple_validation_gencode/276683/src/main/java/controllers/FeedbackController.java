package controllers;

import dto.FeedbackDTO;
import entities.UserSession;
import repositories.DataAccessException;
import usecases.ViewFeedbackForSiteUseCase;
import java.util.Collections;
import java.util.List;

/**
 * Controller (Interface Adapter) as per the class diagram.
 * Handles the view feedback request, validates authentication, and calls the use case.
 */
public class FeedbackController {
    private ViewFeedbackForSiteUseCase viewFeedbackUseCase;
    private UserSession userSession;
    private boolean authenticationErrorFlag = false; // flag to indicate authentication failure

    /**
     * Constructor as per class diagram.
     * @param useCase the use case interactor
     * @param session the user session for authentication
     */
    public FeedbackController(ViewFeedbackForSiteUseCase useCase, UserSession session) {
        this.viewFeedbackUseCase = useCase;
        this.userSession = session;
    }

    /**
     * Handles the view feedback request for a given site.
     * Implements the sequence diagram flow.
     * @param siteId the site identifier
     * @return a list of FeedbackDTO objects; empty list if no data or error
     */
    public List<FeedbackDTO> viewFeedback(String siteId) {
        // reset flag
        authenticationErrorFlag = false;
        // Step 2.1 in sequence diagram: Validate UserSession
        if (!userSession.validateAuthentication()) {
            // Invalid authentication -> return empty list and set flag
            System.out.println("Authentication Error: User is not authenticated.");
            authenticationErrorFlag = true;
            return Collections.emptyList();
        }

        // Valid Authentication -> proceed
        try {
            // Step 3 in sequence diagram: call use case
            List<FeedbackDTO> feedbacks = viewFeedbackUseCase.execute(siteId);
            // Step 10 in sequence diagram: return List<FeedbackDTO>
            return feedbacks;
        } catch (DataAccessException e) {
            // Connection interrupted as per sequence diagram
            System.out.println("Connection Lost - Please try again");
            return Collections.emptyList();
        } catch (Exception e) {
            // Other errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Method to check if the last call failed due to authentication.
     * Used by UI to show authentication-specific error.
     */
    public boolean isAuthenticationError() {
        return authenticationErrorFlag;
    }
}