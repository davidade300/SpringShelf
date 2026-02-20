package com.david.bookshelf.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Chapter> chapters = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer version;
    private LocalDateTime releaseDate;
    private String author;
    private String publisher;
    @Column(unique = true, nullable = false)
    private String isbn10;
    @Column(unique = true, nullable = false)
    private String isbn13;
    @Column(name = "cover_img_url")
    private String coverImgUrl;

    public Book() {
    }

    public Book(Long id, String title, Integer version, LocalDateTime releaseDate, String author, String publisher, String isbn10, String isbn13, String coverImgUrl) {
        this.id = id;
        this.title = title;
        this.version = version;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.coverImgUrl = coverImgUrl;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Verifica se ha um capitulo com o mesmo titulo
     *
     * @param title string contendo o titulo de um capitulo
     * @return boolean True = titulo duplicado
     */
    public boolean verifyChapterTitleDuplicity(String title) {
        return this.chapters.stream()
                .anyMatch(chapter -> Objects.equals(chapter.getTitle().toLowerCase(), title.toLowerCase()));
    }

    /**
     * Funcao responsável por adicionar capitulos a um livro.
     *
     * @param title   String nao nula.
     * @param summary String nao nula
     * @return Chapter
     */
    public Chapter addChapter(String title, String summary) {

        if (title == null || title.isBlank()) throw new IllegalArgumentException("Chapter title cannot be empty");

        if (verifyChapterTitleDuplicity(title))
            throw new IllegalStateException("A chapter with this title alread exists in this book");

        Chapter chapter = new Chapter();
        chapter.setTitle(title);
        chapter.setSummary(summary);
        chapter.setBook(this);
        chapter.setCreatedAt(LocalDateTime.now());
        chapter.setLastUpdatedAt(LocalDateTime.now());

        this.chapters.add(chapter);
        return chapter;
    }

    /**
     * funcao responsavel por encontrar um capitulo e remove-lo
     *
     * @param id id de um capitulo a ser buscado na coleção de capitulos e deletado
     */
    public void removeChapter(Long id) {
        Chapter chapter = this.chapters.stream().filter(ch -> Objects.equals(ch.getId(), id)).
                findFirst().
                orElseThrow(() -> new IllegalArgumentException("Chapter not found in this book"));

        this.chapters.remove(chapter);
        chapter.setBook(null);
    }
}
