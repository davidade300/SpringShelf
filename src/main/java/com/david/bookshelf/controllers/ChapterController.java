package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.chapter.ChapterUpdate;
import com.david.bookshelf.services.ChapterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }


    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getChapters(@PathVariable Long bookId) {
        List<ChapterDTO> chapters = chapterService.listBookChapters(bookId);
        return ResponseEntity.ok(chapters);
    }

    @PatchMapping("/{chapterId}")
    public ResponseEntity<ChapterDTO> update(@PathVariable Long bookId, @PathVariable Long chapterId,
                                             @Valid @RequestBody ChapterUpdate chapterUpdate) {

        ChapterDTO chapter = chapterService.update(bookId, chapterId, chapterUpdate);
        return ResponseEntity.ok(chapter);
    }
}
