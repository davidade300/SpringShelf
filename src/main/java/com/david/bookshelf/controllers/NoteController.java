package com.david.bookshelf.controllers;

import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.dtos.note.NoteRequest;
import com.david.bookshelf.dtos.note.NoteUpdate;
import com.david.bookshelf.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/chapters/{chapterId}/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    public ResponseEntity<List<NoteDTO>> getChapterNotes(@PathVariable Long bookId, @PathVariable Long chapterId) {
        List<NoteDTO> notes = noteService.listChapterNotes(bookId, chapterId);

        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long bookId, @PathVariable Long chapterId,
                                               @PathVariable Long noteId) {
        NoteDTO note = noteService.findNoteById(bookId, chapterId, noteId);

        return ResponseEntity.ok(note);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> insertNote(@PathVariable Long bookId, @PathVariable Long chapterId,
                                              @Valid @RequestBody NoteRequest noteRequest) {
        NoteDTO saved = noteService.insert(bookId, chapterId, noteRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);

    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable Long bookId, @PathVariable Long chapterId,
                                              @PathVariable Long noteId, @Valid @RequestBody NoteUpdate noteUpdate) {

        NoteDTO note = noteService.update(bookId, chapterId, noteId, noteUpdate);

        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long bookId, @PathVariable Long chapterId,
                                           @PathVariable Long noteId) {
        noteService.delete(bookId, chapterId, noteId);

        return ResponseEntity.noContent().build();
    }

}
