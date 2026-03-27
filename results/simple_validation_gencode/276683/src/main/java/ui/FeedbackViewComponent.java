package ui;

import controllers.FeedbackController;
import dto.FeedbackDTO;
import entities.Site;
import repositories.ISiteRepository;
import java.util.List;

/**
 * UI Boundary (e.g., Web Page Component) as per the class diagram.
 * Displays site list and feedback, and handles site selection.
 */
public class FeedbackViewComponent {
    private FeedbackController controller;
    private ISiteRepository siteRepository; // Added to satisfy site list retrieval requirement

    /**
     * Constructor as per class diagram.
     * @param controller the feedback controller
     * @param siteRepository the site repository to load site list
     */
    public FeedbackViewComponent(FeedbackController controller, ISiteRepository siteRepository) {
        this.controller = controller;
        this.siteRepository = siteRepository;
    }

    /**
     * Displays a list of sites (e.g., in a dropdown).
     * @param sites the list of sites
     */
    public void displaySiteList(List<Site> sites) {
        System.out.println("=== Site List ===");
        for (Site site : sites) {
            System.out.println(site.getSiteId() + " - " + site.getName() + " (" + site.getLocation() + ")");
        }
        System.out.println();
    }

    /**
     * Displays a list of feedback DTOs.
     * @param feedbacks the list of FeedbackDTO objects
     */
    public void displayFeedback(List<FeedbackDTO> feedbacks) {
        if (feedbacks.isEmpty()) {
            System.out.println("No Feedback Available");
            return;
        }
        System.out.println("=== Feedback List ===");
        for (FeedbackDTO dto : feedbacks) {
            System.out.println("ID: " + dto.getFeedbackId() + " | " +
                    dto.getTimestamp() + " | " +
                    dto.getContent());
        }
        System.out.println();
    }

    /**
     * Handles the site selection event (triggered by UI).
     * Calls controller to get feedback for the site and displays it.
     * @param siteId the selected site identifier
     */
    public void handleSiteSelection(String siteId) {
        System.out.println("Selected Site ID: " + siteId);
        List<FeedbackDTO> feedbacks = controller.viewFeedback(siteId);
        if (controller.isAuthenticationError()) {
            System.out.println("Access Denied: Authentication required.");
        } else {
            displayFeedback(feedbacks);
        }
    }

    /**
     * Loads the site list from repository and displays it.
     * Added to satisfy site list retrieval requirement.
     */
    public void loadSiteList() {
        List<Site> sites = siteRepository.findAll();
        displaySiteList(sites);
    }

    /**
     * Simulates the main flow: load sites, then simulate selection of a site.
     * This method is not in the diagram but added to make the component runnable.
     */
    public void simulateUserInteraction() {
        // Step 0: load site list (assumed to have been selected from SearchSite results)
        loadSiteList();
        // Simulate the agency operator selecting a site (e.g., SITE001)
        handleSiteSelection("SITE001");
    }
}