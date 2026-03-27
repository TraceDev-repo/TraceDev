package com.example.mapper;

import com.example.news.NewsDTO;
import com.example.news.NewsEntity;
import java.time.LocalDateTime;

/**
 * Mapper to convert between NewsEntity and NewsDTO.
 */
public class NewsMapper {

    /**
     * Converts a NewsEntity to a NewsDTO.
     * @param entity the source entity.
     * @return the DTO.
     */
    public NewsDTO toDTO(NewsEntity entity) {
        if (entity == null) return null;
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setAuthor(entity.getAuthor());
        dto.setPublicationDate(entity.getPublicationDate());
        dto.setVersion(entity.getVersion());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        return dto;
    }

    /**
     * Converts a NewsDTO to a NewsEntity.
     * @param dto the source DTO.
     * @return the entity.
     */
    public NewsEntity toEntity(NewsDTO dto) {
        if (dto == null) return null;
        NewsEntity entity = new NewsEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setAuthor(dto.getAuthor());
        entity.setPublicationDate(dto.getPublicationDate());
        entity.setVersion(dto.getVersion());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        return entity;
    }
}