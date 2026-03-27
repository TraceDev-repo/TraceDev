package repositories;

import entities.Feedback;
import java.util.List;

/**
 * Repository interface for Feedback entities as per the class diagram.
 */
public interface IFeedbackRepository {
    /**
     * Finds all feedback entries for a given site.
     * @param siteId the site identifier
     * @return a list of Feedback objects for the site
     */
    List<Feedback> findBySiteId(String siteId);
}