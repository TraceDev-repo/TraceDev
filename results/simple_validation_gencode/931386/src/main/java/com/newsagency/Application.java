package com.newsagency;

import com.newsagency.model.AgencyOperator;
import com.newsagency.model.SessionManager;
import com.newsagency.service.AuthService;
import com.newsagency.service.NewsService;
import com.newsagency.service.NewsValidator;
import com.newsagency.controller.NewsController;
import com.newsagency.dto.NewsDTO;
import com.newsagency.repository.NewsRepository;
import com.newsagency.repository.NewsRepositoryImpl;
import com.newsagency.util.TransactionManager;
import java.util.Date;

/**
 * Main application class that sets up the components and simulates the news insertion flow.
 */
public class Application {

    public static void main(String[] args) {
        // Setup components as per class diagram dependencies
        SessionManager session = new SessionManager("session123", "operator1",
                new Date(System.currentTimeMillis() + 3600000)); // expires in 1 hour
        AuthService authService = new AuthService(session);

        // Simulate authentication check (Entry Condition)
        if (!authService.checkAuthentication()) {
            authService.displayLoginRequired();
            return;
        }

        NewsValidator validator = new NewsValidator();
        NewsRepository repository = new NewsRepositoryImpl();
        TransactionManager txManager = new TransactionManager();
        NewsService newsService = new NewsService(validator, repository, txManager);
        NewsController controller = new NewsController(authService, newsService);

        AgencyOperator operator = new AgencyOperator("operator1");

        // Begin the main success scenario
        System.out.println("=== Main Success Scenario ===");
        controller.activateNewsInsertion();

        // Simulate operator filling form
        NewsDTO newsData = new NewsDTO("Breaking News",
                "This is the content of the breaking news.", "John Doe");

        // Simulate submission and confirmation
        controller.submitNewsForm(newsData);

        // Alternative flow: validation failure
        System.out.println("\n=== Alternative Flow: Validation Failure ===");
        NewsDTO invalidNews = new NewsDTO("", "", ""); // empty fields
        controller.submitNewsForm(invalidNews);

        // Alternative flow: cancellation (simulated by calling cancelOperation directly)
        System.out.println("\n=== Alternative Flow: Cancellation at Form Display ===");
        controller.cancelOperation("Operation cancelled at form display");
    }
}