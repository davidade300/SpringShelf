package com.david.bookshelf.dtos.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteUpdate {
    @NotBlank
    @Size(min = 10)
    private String content;

    public NoteUpdate(String content) {
        this.content = content;
    }

    public NoteUpdate() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
