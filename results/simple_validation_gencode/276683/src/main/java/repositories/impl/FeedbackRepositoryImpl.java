package repositories.impl;

import entities.Feedback;
import repositories.DataAccessException;
import repositories.IFeedbackRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IFeedbackRepository as per the class diagram.
 * Uses an in-memory data source for simplicity.
 * In a real application, this would connect to a database.
 */
public class FeedbackRepositoryImpl implements IFeedbackRepository {
    private Object dataSource; // Placeholder as per diagram; not used in this simple implementation
    private List<Feedback> feedbackList;
    private boolean simulateConnectionFailure = false; // added to simulate connection interruption

    /**
     * Constructor initializes the in-memory data source.
     */
    public FeedbackRepositoryImpl() {
        feedbackList = new ArrayList<>();
        // Simulate some dummy feedback data
        feedbackList.add(new Feedback("FB001", "SITE001", "Beautiful park, very clean.", LocalDateTime.now().minusDays(2)));
        feedbackList.add(new Feedback("FB002", "SITE001", "Needs more benches.", LocalDateTime.now().minusDays(1)));
        feedbackList.add(new Feedback("FB003", "SITE002", "Amazing view from the top.", LocalDateTime.now().minusHours(5)));
        dataSource = feedbackList; // initialize dataSource to reflect actual usage
    }

    public List<Feedback> findBySiteId(String siteId) {
        // Simulate connection interruption as per sequence diagram
        if (simulateConnectionFailure) {
            throw new DataAccessException("Connection to database lost.");
        }
        // Simulate database query: filter feedbacks by siteId
        return feedbackList.stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Setter to simulate connection failure for testing purposes.
     */
    public void setSimulateConnectionFailure(boolean simulate) {
        this.simulateConnectionFailure = simulate;
    }
}