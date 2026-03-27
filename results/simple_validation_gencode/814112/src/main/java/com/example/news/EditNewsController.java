package com.example.news;

import com.example.connection.DisconnectionListener;
import com.example.confirmation.ConfirmationService;
import com.example.connection.ConnectionMonitor;
import com.example.exceptions.ConnectionError;
import com.example.exceptions.OptimisticLockException;
import com.example.integrity.DataIntegrityService;
import com.example.mapper.NewsMapper;
import com.example.notification.NotificationService;
import com.example.repository.NewsRepository;
import com.example.transaction.TransactionManager;
import com.example.validation.NewsValidator;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller for editing news. Orchestrates the flow of loading, validating,
 * and saving news data, and handles user interactions.
 */
public class EditNewsController implements DisconnectionListener {

    private NewsRepository newsRepository;
    private NewsMapper newsMapper;
    private NewsValidator newsValidator;
    private TransactionManager transactionManager;
    private NotificationService notificationService;
    private DataIntegrityService dataIntegrityService;
    private ConnectionMonitor connectionMonitor;
    private ConfirmationService confirmationService;

    private List<NewsDTO> currentNewsList;
    private NewsEntity currentEditingEntity;

    /**
     * Constructor for dependency injection.
     */
    public EditNewsController(NewsRepository newsRepository,
                              NewsMapper newsMapper,
                              NewsValidator newsValidator,
                              TransactionManager transactionManager,
                              NotificationService notificationService,
                              DataIntegrityService dataIntegrityService,
                              ConnectionMonitor connectionMonitor,
                              ConfirmationService confirmationService) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.newsValidator = newsValidator;
        this.transactionManager = transactionManager;
        this.notificationService = notificationService;
        this.dataIntegrityService = dataIntegrityService;
        this.connectionMonitor = connectionMonitor;
        this.confirmationService = confirmationService;

        // Start background connection monitoring
        connectionMonitor.startMonitoring();
        connectionMonitor.addDisconnectionListener(this);
    }

    /**
     * Called when the operator starts the editing process.
     */
    public void activateEditing() {
        // In a real UI, this might initialize the view.
        System.out.println("EditNewsController: Editing activated.");
    }

    /**
     * Loads all news as a list for selection.
     * @return List of NewsDTO objects.
     */
    public List<NewsDTO> loadNewsList() {
        // Flow of Events 2: System views all news in a form
        List<NewsEntity> entities = newsRepository.findAll();
        List<NewsDTO> dtos = new ArrayList<>();
        for (NewsEntity entity : entities) {
            dtos.add(newsMapper.toDTO(entity));
        }
        currentNewsList = dtos; // Keep reference for potential cancellation
        return dtos;
    }

    /**
     * Validates that a news ID exists in the repository before loading.
     * Added to satisfy requirement: Flow of Events 3 - validation before loading.
     * @param newsId the ID to validate.
     * @return true if the ID exists.
     */
    public boolean validateNewsId(String newsId) {
        return newsRepository.existsById(newsId);
    }

    /**
     * Selects a news item for editing.
     * @param newsId the ID of the news to edit.
     * @return the NewsDTO for the selected news, or null if not found.
     */
    public NewsDTO selectNews(String newsId) {
        // Flow of Events 3: Agency Operator selects a news from the list
        // Validation added per sequence diagram
        if (!validateNewsId(newsId)) {
            displayNotification("Selected news does not exist", NotificationType.ERROR);
            return null;
        }
        return loadNewsData(newsId);
    }

    /**
     * Loads news data for editing by ID.
     * @param id the news ID.
     * @return NewsDTO with the data, or null if not found.
     */
    public NewsDTO loadNewsData(String id) {
        // Flow of Events 4/5: System loads news for editing
        Optional<NewsEntity> optionalEntity = newsRepository.findById(id);
        if (optionalEntity.isPresent()) {
            NewsEntity entity = optionalEntity.get();
            currentEditingEntity = entity; // Keep reference for version checks and audit
            return newsMapper.toDTO(entity);
        } else {
            // This case should be caught by validateNewsId, but kept for safety.
            return null;
        }
    }

    /**
     * Validates the news data.
     * @param dto the NewsDTO to validate.
     * @return true if valid.
     */
    public boolean validateNewsData(NewsDTO dto) {
        // Flow of Events 9: System validates the modified data
        return newsValidator.validate(dto);
    }

    /**
     * Processes a form submission.
     * @param modifiedNewsDTO the modified data from the form.
     * @return true if the save process was initiated successfully.
     */
    public boolean submitForm(NewsDTO modifiedNewsDTO) {
        // Flow of Events 8: Agency Operator submits the form
        // Step 1: Validate data
        if (!validateNewsData(modifiedNewsDTO)) {
            displayNotification("Validation failed", NotificationType.ERROR);
            return false;
        }

        // Step 2: Validate business rules
        NewsEntity entity = newsMapper.toEntity(modifiedNewsDTO);
        if (!dataIntegrityService.validateBusinessRules(entity)) {
            displayNotification("Business rules validation failed", NotificationType.ERROR);
            return false;
        }

        // Step 3: Ask for confirmation (Flow of Events 10)
        boolean confirmed = confirmationService.requestConfirmation("Confirm save?");
        if (!confirmed) {
            // User cancelled in confirmation dialog
            cancelOperation();
            return false;
        }

        // Step 4: Proceed with saving
        return confirmAndSave(modifiedNewsDTO);
    }

    /**
     * Confirms and saves the news data after validation.
     * @param dto the NewsDTO to save.
     * @return true if saved successfully.
     */
    public boolean confirmAndSave(final NewsDTO dto) {
        // Flow of Events 11: System saves the modified data
        try {
            transactionManager.executeInTransaction(new Runnable() {
                @Override
                public void run() {
                    // Convert to entity
                    NewsEntity entity = newsMapper.toEntity(dto);

                    // Version validation
                    if (!newsValidator.isVersionValid(currentEditingEntity.getVersion(), dto.getVersion())) {
                        throw new OptimisticLockException("Version conflict - data was modified by another user");
                    }

                    // Consistency check before update
                    if (!dataIntegrityService.checkConsistencyBeforeUpdate(entity)) {
                        throw new IllegalStateException("Data consistency check failed");
                    }

                    // Save with explicit version for optimistic locking
                    NewsEntity savedEntity = newsRepository.save(entity, dto.getVersion());

                    // Log audit trail
                    dataIntegrityService.logAuditTrail(currentEditingEntity, savedEntity, "AgencyOperator");
                }
            });

            // If we reach here, the transaction succeeded.
            displayNotification("News successfully amended", NotificationType.SUCCESS);
            return true;

        } catch (OptimisticLockException e) {
            // Explicit rollback transaction on version conflict as per sequence diagram
            transactionManager.rollbackTransaction();
            displayNotification(e.getMessage(), NotificationType.ERROR);
            return false;
        } catch (ConnectionError e) {
            displayNotification("Connection lost - please retry", NotificationType.ERROR);
            return false;
        } catch (Exception e) {
            displayNotification("Save operation failed: " + e.getMessage(), NotificationType.ERROR);
            return false;
        }
    }

    /**
     * Cancels the current operation and cleans up.
     */
    public void cancelOperation() {
        cleanup();
        displayNotification("Operation cancelled", NotificationType.INFO);
    }

    /**
     * Cleans up resources and resets state.
     */
    private void cleanup() {
        currentNewsList = null;
        currentEditingEntity = null;
        // Additional cleanup as needed
    }

    /**
     * Handles connection errors detected by the ConnectionMonitor.
     * Satisfies requirement: Exit Conditions - server disconnection handling.
     */
    @Override
    public void onDisconnectionDetected() {
        handleConnectionError();
    }

    /**
     * Invoked when a connection error occurs.
     */
    public void handleConnectionError() {
        displayNotification("Server connection lost", NotificationType.ERROR);
        // In a real system, might disable UI elements or trigger a retry logic.
    }

    /**
     * Displays a notification via the NotificationService.
     * Added to satisfy requirement: Exit Conditions - successful amendment notification.
     * @param message the message to display.
     * @param type the type of notification.
     */
    public void displayNotification(String message, NotificationType type) {
        notificationService.sendNotification(message, type);
    }
}