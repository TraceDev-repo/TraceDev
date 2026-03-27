package com.example.usecase.command;

import com.example.domain.CulturalHeritage;
import com.example.domain.repository.ICulturalHeritageRepository;
import java.util.UUID;

/**
 * Command class representing the deletion of a cultural heritage.
 * Implements the command pattern.
 */
public class DeleteCulturalHeritageCommand {
    private String heritageId;
    private String id;
    private ICulturalHeritageRepository repository;

    public DeleteCulturalHeritageCommand(String heritageId, ICulturalHeritageRepository repository) {
        this.heritageId = heritageId;
        this.id = UUID.randomUUID().toString();
        this.repository = repository;
    }

    public String getId() {
        return id;
    }

    public String getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(String heritageId) {
        this.heritageId = heritageId;
    }

    /**
     * Executes the command by deleting the cultural heritage.
     */
    public void execute() {
        CulturalHeritage heritage = repository.findById(heritageId);
        if (heritage != null) {
            repository.delete(heritageId);
        } else {
            throw new RuntimeException("Cultural heritage not found with ID: " + heritageId);
        }
    }
}