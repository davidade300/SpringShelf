package com.david.bookshelf.dtos.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteRequest {

    @NotBlank
    @Size(min = 3, max = 80)
    private String title;

    @NotBlank
    @Size(min = 10)
    private String content;

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
