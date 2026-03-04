package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.book.BookRequest;
import com.david.bookshelf.dtos.book.BookUpdate;
import com.david.bookshelf.dtos.book.BookWithChapters;
import com.david.bookshelf.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/books")
@Tag(name = "Books", description = "Operacoes disponiveis para a entidade Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @Operation(summary = "Retorna todos os livros paginados")
    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.findAllBooks(pageable));
    }

    @Operation(summary = "Retorna um livro e seus capitulos")
    @GetMapping("with_chapters/{bookId}")
    public ResponseEntity<BookWithChapters> getBookWithChapters(@PathVariable Long bookId) {
        BookWithChapters dto = bookService.findBookWithChapters(bookId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Retorna um livro a partir de seu Id")
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        BookDTO dto = bookService.findBookById(bookId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Cria um livro")
    @PostMapping
    public ResponseEntity<BookDTO> insertBook(@Valid @RequestBody BookRequest bookRequest) {
        BookDTO saved = bookService.insert(bookRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @Operation(summary = "Atualiza a descrição de um livro a partir do seu Id")
    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookDescription(@PathVariable Long id, @Valid @RequestBody BookUpdate bookUpdate) {
        BookDTO updated = bookService.update(id, bookUpdate);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deleta um livro a partir do seu Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
