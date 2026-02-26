package com.david.bookshelf.dtos.chapter;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO para instanciação de capitulos
 */
public class ChapterRequest {
    @Size(min = 3, max = 80)
    @NotBlank
    String title;
    @Size(min = 10, max = 255)
    @NotBlank
    String summary;
    LocalDateTime createdAt;

    public ChapterRequest() {
    }

    public ChapterRequest(String title, String summary) {
        this.title = title;
        this.summary = summary;
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getSummary() {
        return summary;
    }

//    public void setSummary(String summary) {
//        this.summary = summary;
//    }

}
