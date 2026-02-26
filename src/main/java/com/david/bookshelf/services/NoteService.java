package com.david.bookshelf.services;

import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.dtos.note.NoteRequest;
import com.david.bookshelf.dtos.note.NoteUpdate;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.entities.Note;
import com.david.bookshelf.repositories.BookRepository;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private final BookRepository bookRepository;

    public NoteService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retorna todas as notas de um chapter
     *
     * @param bookId    id do book contendo o chapter que contem as notas e da note
     * @param chapterId id do chapter id do book que contém a note
     * @return List de NoteDTO de um chapter
     */
    @Transactional(readOnly = true)
    public List<NoteDTO> listChapterNotes(Long bookId, Long chapterId) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);

        return chapter.getNotes().stream().map(NoteDTO::new).toList();
    }

    /**
     * Encontra uma note a partir do id de um book, de um chapter e da note
     *
     * @param bookId    id do book que contém o chapter que contem a note a ser buscada
     * @param chapterId id do chapter que contem a note a ser buscada
     * @param noteId    id da note a ser buscada
     * @return NoteDTO da note a ser buscada
     */
    @Transactional(readOnly = true)
    public NoteDTO findNoteById(Long bookId, Long chapterId, Long noteId) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);
        Note note = loadNoteByIdOrThrow(chapter, noteId);

        return new NoteDTO(note);
    }

    /**
     * Adiciona uma Note a um Chapter
     *
     * @param bookId      id do book que contém o chapter que contem a note a ser inserida
     * @param chapterId   id do chapter que contem a note a ser inserida
     * @param noteRequest objeto NoteRequest que contem os dados da nova Note
     * @return NoteDTO da note inserida
     */
    @Transactional
    public NoteDTO insert(Long bookId, Long chapterId, NoteRequest noteRequest) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);

        Note note = chapter.addNote(noteRequest.getTitle(), noteRequest.getContent());
        bookRepository.save(book);

        return new NoteDTO(note);
    }

    /**
     * Altera uma Note de um Chapter
     *
     * @param bookId     id do book que contém o chapter que contem a note a ser atualizada
     * @param chapterId  id do chapter que contem a note a ser atualizada
     * @param noteUpdate objeto NoteUpdate que contem os dados a serem atualizados na note
     * @return NoteDTO da note atualizada
     */
    @Transactional
    public NoteDTO update(Long bookId, Long chapterId, Long noteId, NoteUpdate noteUpdate) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);
        Note note = loadNoteByIdOrThrow(chapter, noteId);

        note.updateContent(noteUpdate.getContent());
        bookRepository.save(book);

        return new NoteDTO(note);
    }


    /**
     * Deleta uma Note de um Chapter
     *
     * @param bookId    id do book que contém o chapter que contem a note a ser deletada
     * @param chapterId id do chapter que contem a note a ser deletada
     * @param noteId    id da note a ser removida
     */
    @Transactional
    public void delete(Long bookId, Long chapterId, Long noteId) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);

        chapter.removeNote(noteId);
        bookRepository.save(book);
    }


    /**
     * Funcao para reduzir a repeticao de "Book book = bookRepository..."
     * e para validacao, para que um service não saiba do outro
     *
     * @param id id do book a ser retornado
     * @return Book
     */
    private Book loadBookByIdOrThrow(Long id) {

        return bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found")
        );
    }

    /**
     * Funcao para reduzir a repeticao de "Chapter Chapter = book.getchapters()..."
     * e para validacao, para que um service não saiba do outro.
     *
     * @param book      Book contendo o chapter a ser buscado.
     * @param chapterId id do chapter a ser buscado
     * @return Chapter
     */
    private Chapter loadChapterByIdOrThrow(Book book, Long chapterId) {

        return book.getChapters().stream().filter(
                        c -> c.getId().equals(chapterId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("No chapter with such id found"));
    }


    /**
     * Funcao para reduzir a repeticao de "Note note = chapter.getNotes()..."
     * e para validacao, para que um service não saiba do outro.
     *
     * @param chapter chapter contendo o chapter a ser buscado.
     * @param noteId  id da Note a ser buscada
     * @return Note
     */
    private Note loadNoteByIdOrThrow(Chapter chapter, Long noteId) {

        return chapter.getNotes().stream().filter(
                        n -> n.getId().equals(noteId)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No Note with such id found"));
    }

}
