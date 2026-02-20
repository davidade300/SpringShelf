package com.david.bookshelf.dtos.note;

public class NoteUpdate {
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
