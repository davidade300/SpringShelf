package com.david.bookshelf.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chapters")
public class Chapter implements Serializable {

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Note> notes = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public Chapter() {
    }

    public Chapter(Long id, String title, String summary, Book book, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.book = book;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Set<Note> getNotes() {
        return notes;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Adiciona uma nota a um capitulo
     *
     * @param title    string, titulo da nota
     * @param content, conteudo da nota
     * @return nova nota linkada ao capitulo que chamou o metodo.
     */
    public Note addNote(String title, String content) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Chapter title cannot be empty");

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setChapter(this);
        note.setCreatedAt(LocalDateTime.now());
        note.setLastUpdatedAt(LocalDateTime.now());

        this.notes.add(note);

        return note;

    }

    /**
     * Deleta uma nota a partir do seu id
     *
     * @param id id da nota a ser deletada
     */
    public void removeNote(Long id) {
        Note note = this.notes.stream().filter(nt -> Objects.equals(nt.getId(), id))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Note not found in this chapter")
                );

        this.notes.remove(note);
        note.setChapter(null);
    }

    /**
     *  Metodo a ser chamado pelo service para atualizar um chapter.
     * @param newSummary novo sumario de um chapter
     */
    public void updateSummary(String newSummary) {
        if (newSummary.isBlank()) throw new IllegalArgumentException("Summary cannot be empty");
        this.summary = newSummary;
        this.lastUpdatedAt = LocalDateTime.now();
    }
}