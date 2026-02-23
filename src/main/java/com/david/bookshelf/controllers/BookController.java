package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.book.BookRequest;
import com.david.bookshelf.dtos.book.BookUpdate;
import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.services.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> findAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.findAllBooks(pageable));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        BookDTO dto = bookService.findBookById(bookId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BookDTO> insertBook(@Valid @RequestBody BookRequest bookRequest) {
        BookDTO saved = bookService.insert(bookRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookDescription(@PathVariable Long id, @Valid @RequestBody BookUpdate bookUpdate) {
        BookDTO updated = bookService.update(id, bookUpdate);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // mover para chapter ou note controller\/


    @GetMapping("/{bookId}/chapters")
    public List<ChapterDTO> getChapters(@PathVariable Long bookId) {
        return bookService.listBookChapters(bookId);
    }

    @GetMapping("/{bookId}/chapters/{chapterId}/notes")
    public List<NoteDTO> getNotes(@PathVariable Long bookId, @PathVariable Long chapterId) {
        return bookService.listChapterNotes(bookId, chapterId);
    }
}
