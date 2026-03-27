package usecases;

import dto.FeedbackDTO;
import entities.Feedback;
import repositories.IFeedbackRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Use Case Interactor for viewing feedback of a specific site as per the class diagram.
 * Converts Feedback entities to FeedbackDTOs.
 */
public class ViewFeedbackForSiteUseCase {
    private IFeedbackRepository feedbackRepository;

    /**
     * Constructor as per class diagram.
     * @param feedbackRepository the repository for feedback data
     */
    public ViewFeedbackForSiteUseCase(IFeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Executes the use case: retrieves feedback for a given site and converts to DTOs.
     * @param siteId the site identifier
     * @return a list of FeedbackDTO objects; empty list if no data
     */
    public List<FeedbackDTO> execute(String siteId) {
        // Step 4 in sequence diagram: call repository
        List<Feedback> feedbacks = feedbackRepository.findBySiteId(siteId);
        List<FeedbackDTO> result = new ArrayList<>();
        // Step 9 in sequence diagram: convert Feedback to FeedbackDTO
        for (Feedback feedback : feedbacks) {
            FeedbackDTO dto = new FeedbackDTO(
                    feedback.getFeedbackId(),
                    feedback.getContent(),
                    feedback.getTimestamp()
            );
            result.add(dto);
        }
        return result;
    }
}