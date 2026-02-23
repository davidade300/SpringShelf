package com.david.bookshelf.dtos.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

/**
 * DTO para instanciação de livros
 */
public class BookRequest {

    @NotBlank
    @Size(min = 4, max = 80)
    private String title;

    @Positive
    private Integer version;

    private LocalDateTime releaseDate;

    @NotBlank
    @Size(min = 3, max = 80)
    private String author;

    @NotBlank
    @Size(min = 3, max = 80)
    private String publisher;

    @NotBlank
    @Size(min = 10, max = 10)
    private String isbn10;

    @NotBlank
    @Size(min = 13, max = 13)
    private String isbn13;

    @NotEmpty
    @URL
    private String coverImgUrl;

    @NotBlank
    @Size(min = 10)
    private String description;

    public BookRequest(String title, Integer version, LocalDateTime releaseDate, String author, String publisher, String isbn10, String isbn13, String coverImgUrl, String description) {
        this.title = title;
        this.version = version;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.coverImgUrl = coverImgUrl;
        this.description = description;
    }

    public BookRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
