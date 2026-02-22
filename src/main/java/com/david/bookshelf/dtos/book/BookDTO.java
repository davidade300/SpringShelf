package com.david.bookshelf.dtos.book;

import com.david.bookshelf.entities.Book;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe para resposta de requisições genéricas(sem a colecao chapters)
 */
public class BookDTO {

    private Long id;
    private String title;
    private Integer version;
    private LocalDateTime releaseDate;
    private String author;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String coverImgUrl;
    private String description;
    private LocalDateTime lastUpdatedAt;


    public BookDTO() {
    }

    public BookDTO(Long id, String title, Integer version, LocalDateTime releaseDate, String author, String publisher, String isbn10, String isbn13, String coverImgUrl, String description, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.version = version;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.coverImgUrl = coverImgUrl;
        this.description = description;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    /**
     * Construtor que cria um Bookdto a partir de um Book
     *
     * @param book livro a ser transformado em DTO
     */
    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        version = book.getVersion();
        releaseDate = book.getReleaseDate();
        author = book.getAuthor();
        publisher = book.getPublisher();
        isbn10 = book.getIsbn10();
        isbn13 = book.getIsbn13();
        coverImgUrl = book.getCoverImgUrl();
        description = book.getDescription();
        lastUpdatedAt = book.getLastUpdatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public Integer getVersion() {
        return version;
    }


    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }


    public String getAuthor() {
        return author;
    }


    public String getPublisher() {
        return publisher;
    }


    public String getIsbn10() {
        return isbn10;
    }


    public String getIsbn13() {
        return isbn13;
    }


    public String getCoverImgUrl() {
        return coverImgUrl;
    }


    public String getDescription() {
        return description;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }
}
