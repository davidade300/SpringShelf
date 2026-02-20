package com.david.bookshelf.dtos.chapter;

import java.time.LocalDateTime;

public class ChapterUpdate {
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
