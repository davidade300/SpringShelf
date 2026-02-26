package com.david.bookshelf.dtos.chapter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ChapterUpdate {
    @Size(min = 10, max = 255)
    @NotBlank
    String summary;

    public ChapterUpdate() {
    }

    public ChapterUpdate(LocalDateTime lastUpdatedAt, String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
