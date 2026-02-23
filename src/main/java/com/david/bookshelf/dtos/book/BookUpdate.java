package com.david.bookshelf.dtos.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookUpdate {

    @NotBlank
    @Size(min = 10,  max=255)
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
