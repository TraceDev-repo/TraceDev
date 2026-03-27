import controllers.FeedbackController;
import entities.UserSession;
import repositories.IFeedbackRepository;
import repositories.ISiteRepository;
import repositories.impl.FeedbackRepositoryImpl;
import repositories.impl.SiteRepositoryImpl;
import ui.FeedbackViewComponent;
import usecases.ViewFeedbackForSiteUseCase;

/**
 * Main class to run the application, simulating the sequence diagram.
 * Creates all necessary objects and triggers the interaction.
 */
public class Main {
    public static void main(String[] args) {
        // Setup the architecture as per class diagram

        // 1. Create repositories (implementations)
        ISiteRepository siteRepository = new SiteRepositoryImpl();
        IFeedbackRepository feedbackRepository = new FeedbackRepositoryImpl();

        // 2. Create use case interactor
        ViewFeedbackForSiteUseCase useCase = new ViewFeedbackForSiteUseCase(feedbackRepository);

        // 3. Create user session (simulating an authenticated agency operator)
        UserSession session = new UserSession("OP001", "AGENCY_OPERATOR", true);

        // 4. Create controller
        FeedbackController controller = new FeedbackController(useCase, session);

        // 5. Create UI component
        FeedbackViewComponent ui = new FeedbackViewComponent(controller, siteRepository);

        // 6. Simulate the scenario: Agency Operator has logged in and site already selected
        System.out.println("=== Simulating Feedback View Use Case ===");
        System.out.println("Assumption: Agency Operator is authenticated and site selected from SearchSite results.\n");
        ui.simulateUserInteraction();

        // Additional test: simulate authentication failure
        System.out.println("\n=== Simulating Authentication Failure ===");
        session.setAuthenticated(false);
        ui.handleSiteSelection("SITE002");
    }
}