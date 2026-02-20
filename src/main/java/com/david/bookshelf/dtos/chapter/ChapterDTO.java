package com.david.bookshelf.dtos.chapter;

import com.david.bookshelf.entities.Chapter;

import java.time.LocalDateTime;

/**
 * Classe para resposta de requisições genéricas(sem a colecao notes)
 */
public class ChapterDTO {
    Long id;
    String title;
    String summary;
    LocalDateTime createdAt;
    LocalDateTime lastUpdatedAt;

    public ChapterDTO(Long id, String title, String summary, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public ChapterDTO(Chapter chapter) {
        id = chapter.getId();
        title = chapter.getTitle();
        summary = chapter.getSummary();
        createdAt = chapter.getCreatedAt();
        lastUpdatedAt = chapter.getLastUpdatedAt();
    }

    public ChapterDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }


}
