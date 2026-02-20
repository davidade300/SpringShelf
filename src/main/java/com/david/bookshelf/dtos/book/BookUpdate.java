package com.david.bookshelf.dtos.book;

public class BookUpdate {
    public String description;

    public BookUpdate() {
    }

    public BookUpdate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
