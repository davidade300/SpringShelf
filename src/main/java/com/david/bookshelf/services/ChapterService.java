package com.david.bookshelf.services;

import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.chapter.ChapterRequest;
import com.david.bookshelf.dtos.chapter.ChapterUpdate;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.repositories.BookRepository;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
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
     * TODO: Adicionar sorting no streaming para ter uma lista ordenada (createdAt)
     *
     * @param bookId id do livro que se quer consulta os chapters
     * @return lista de ChapterDTO
     */
    @Transactional(readOnly = true)
    public List<ChapterDTO> listBookChapters(Long bookId) {
        Book book = loadBookByIdOrThrow(bookId);

        return book.getChapters().stream().map(ChapterDTO::new).toList();
    }


    @Transactional
    public ChapterDTO insert(Long bookId, ChapterRequest chapterRequest){

    }

    /**
     * Atualiza um chapter de um livro a partir de um DTO ChapterUpdate.
     * seleciona o livro a partir do id, selecionando o chapter dentro deste livro tambem por id.
     *
     * @param bookId        id do livro que contem o chapter a ser atualizado
     * @param chapterID     id do chapter a ser atualizado
     * @param chapterUpdate DTO contendo os dados para atualizar o Chapter
     * @return ChapterDTO
     */
    @Transactional
    public ChapterDTO update(Long bookId, Long chapterID, ChapterUpdate chapterUpdate) {
        Book book = loadBookByIdOrThrow(bookId);

        Chapter chapter = book.getChapters().stream().filter(
                c -> c.getId().equals(chapterID)
        ).findFirst().orElseThrow(() -> new ResourceNotFoundException("No chapter with such id found"));

        chapter.updateSummary(chapterUpdate.getSummary());
        bookRepository.save(book);

        return new ChapterDTO(chapter);

    }

    /**
     * Funcao para reduzir a repeticao de "Book book = bookRepository..."
     * e para validacao, para que um service não saiba do outro, os 3 tem essa funcao
     *
     * @param id id do book a ser retornado
     * @return Book
     */
    private Book loadBookByIdOrThrow(Long id) {

        return bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found")
        );
    }
}
