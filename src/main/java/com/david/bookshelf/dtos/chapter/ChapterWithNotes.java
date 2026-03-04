package com.david.bookshelf.dtos.chapter;

import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.entities.Chapter;

import java.time.LocalDateTime;
import java.util.List;

public class ChapterWithNotes {
    private Long id;
    private String title;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private List<NoteDTO> notes;

    public ChapterWithNotes(Long id, String title, String summary, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public ChapterWithNotes(Chapter chapter) {
        id = chapter.getId();
        title = chapter.getTitle();
        summary = chapter.getSummary();
        createdAt = chapter.getCreatedAt();
        lastUpdatedAt = chapter.getLastUpdatedAt();
        notes = chapter.getNotes().stream().map(NoteDTO::new).toList();
    }


    public ChapterWithNotes() {
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

    public List<NoteDTO> getNotes() {
        return notes;
    }
}
