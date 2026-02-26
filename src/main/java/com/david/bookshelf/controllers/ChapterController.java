package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.chapter.ChapterRequest;
import com.david.bookshelf.dtos.chapter.ChapterUpdate;
import com.david.bookshelf.services.ChapterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService/*, BookService bookService*/) {
        this.chapterService = chapterService;
    }


    @GetMapping("/{chapterId}")
    public ResponseEntity<ChapterDTO> getChapterById(@PathVariable Long bookId, @PathVariable Long chapterId) {
        ChapterDTO chapter = chapterService.findChapterById(bookId, chapterId);

        return ResponseEntity.ok(chapter);
    }

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> listBookChapters(@PathVariable Long bookId) {
        List<ChapterDTO> chapters = chapterService.listBookChapters(bookId);
        return ResponseEntity.ok(chapters);
    }

    @PostMapping
    public ResponseEntity<ChapterDTO> insert(@PathVariable Long bookId, @Valid @RequestBody ChapterRequest chapterRequest) {
        ChapterDTO saved = chapterService.insert(bookId, chapterRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{chapterId}")
    public ResponseEntity<ChapterDTO> update(@PathVariable Long bookId, @PathVariable Long chapterId,
                                             @Valid @RequestBody ChapterUpdate chapterUpdate) {

        ChapterDTO chapter = chapterService.update(bookId, chapterId, chapterUpdate);
        return ResponseEntity.ok(chapter);
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long bookId, @PathVariable Long chapterId) {
        chapterService.delete(bookId, chapterId);

        return ResponseEntity.noContent().build();
    }
}
