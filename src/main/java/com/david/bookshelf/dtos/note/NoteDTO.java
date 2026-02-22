package com.david.bookshelf.dtos.note;


import com.david.bookshelf.entities.Note;

import java.time.LocalDateTime;

public class NoteDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public NoteDTO(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public NoteDTO(Note note) {
        id = note.getId();
        title = note.getTitle();
        content = note.getContent();
        createdAt = note.getCreatedAt();
        lastUpdatedAt = note.getLastUpdatedAt();
    }

    public NoteDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }
}
