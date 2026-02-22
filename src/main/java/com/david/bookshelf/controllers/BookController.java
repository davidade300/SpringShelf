package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping
    public List<BookDTO> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookDTO getBook(@PathVariable Long bookId) {
        return bookService.findBookById(bookId);
    }

    @GetMapping("/{bookId}/chapters")
    public List<ChapterDTO> getChapters(@PathVariable Long bookId) {
        return bookService.listBookChapters(bookId);
    }

    @GetMapping("/{bookId}/chapters/{chapterId}/notes")
    public List<NoteDTO> getNotes(@PathVariable Long bookId, @PathVariable Long chapterId) {
        return bookService.listChapterNotes(bookId, chapterId);
    }
}
