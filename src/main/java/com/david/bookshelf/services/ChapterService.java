package com.david.bookshelf.services;

import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.chapter.ChapterRequest;
import com.david.bookshelf.dtos.chapter.ChapterUpdate;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.repositories.BookRepository;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChapterService {
    private final BookRepository bookRepository;

    public ChapterService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * retorna uma lista de ChapterDTO para o controller
     * TODO: Adicionar sorting no streaming para ter uma lista ordenada (createdAt),
     * nao usei pageable pois dificilmente um livro e tao grande assim,
     *
     * @param bookId id do livro que se quer consulta os chapters
     * @return lista de ChapterDTO
     */
    @Transactional(readOnly = true)
    public List<ChapterDTO> listBookChapters(Long bookId) {
        Book book = loadBookByIdOrThrow(bookId);

        return book.getChapters().stream().map(ChapterDTO::new).toList();
    }


    @Transactional(readOnly = true)
    public ChapterDTO findChapterById(Long bookId, Long chapterId) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);


        return new ChapterDTO(chapter);
    }

    /**
     * Adiciona um novo chapter a um livro a partir de um DTO ChapterRequest
     *
     * @param bookId         Id do livro que receberá o capitulo
     * @param chapterRequest DTO com os dados para o novo livro
     * @return ChapterDTO com os dados do novo capitulo
     */
    @Transactional
    public ChapterDTO insert(Long bookId, ChapterRequest chapterRequest) {
        Book book = loadBookByIdOrThrow(bookId);
        Chapter chapter = book.addChapter(chapterRequest.getTitle(), chapterRequest.getSummary());

        bookRepository.save(book);

        return new ChapterDTO(chapter);
    }

    /**
     * Atualiza um chapter de um livro a partir de um DTO ChapterUpdate.
     * seleciona o livro a partir do id, selecionando o chapter dentro deste livro tambem por id.
     *
     * @param bookId        id do livro que contem o chapter a ser atualizado
     * @param chapterId     id do chapter a ser atualizado
     * @param chapterUpdate DTO contendo os dados para atualizar o Chapter
     * @return ChapterDTO
     */
    @Transactional
    public ChapterDTO update(Long bookId, Long chapterId, ChapterUpdate chapterUpdate) {
        Book book = loadBookByIdOrThrow(bookId);

        Chapter chapter = loadChapterByIdOrThrow(book, chapterId);
        chapter.updateSummary(chapterUpdate.getSummary());
        bookRepository.save(book);

        return new ChapterDTO(chapter);

    }

    @Transactional
    public void delete(Long bookId, Long chapterId) {
        Book book = loadBookByIdOrThrow(bookId);
        book.removeChapter(chapterId);

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
     * @param chapterId id do capitulo a ser buscado
     * @return Chapter
     */
    private Chapter loadChapterByIdOrThrow(Book book, Long chapterId) {

        return book.getChapters().stream().filter(
                c -> c.getId().equals(chapterId)
        ).findFirst().orElseThrow(() -> new EntityNotFoundException("No chapter with such id found"));
    }
}
