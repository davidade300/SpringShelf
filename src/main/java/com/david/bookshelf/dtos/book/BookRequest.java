package com.david.bookshelf.dtos.book;

import java.time.LocalDateTime;

/**
 * DTO para instanciação de livros
 */
public class BookRequest {

    private String title;
    private Integer version;
    private LocalDateTime releaseDate;
    private String author;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String coverImgUrl;
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
