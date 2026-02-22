package com.david.bookshelf.services;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Retorna um livro a partir de seu id
     *
     * @param id id do livro a ser encontrado
     * @return objeto do tipo BookDTO
     */
    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book with id " + id + " not found")
        );
        return new BookDTO(book);
    }

    /**
     * rtorna uma lista de BookDTO
     *
     * @return ListBookDTO
     */
    @Transactional(readOnly = true)
    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDTO::new).toList();
    }

    /**
     * retorna uma lista de ChapterDTO para o controller
     * TODO: Criar excecao de recurso nao encontrando, adicionar sorting no streaming
     *  para ter uma lista ordenada (createdAt)
     *
     * @param id id do livro que se quer consulta os capitulos
     * @return lista de ChapterDTO
     */
    @Transactional(readOnly = true)
    public List<ChapterDTO> listBookChapters(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No book with such id found"));


        return book.getChapters().stream().map(ChapterDTO::new).toList();
    }

    /**
     * Lista as notas de um capitulo a partir do id de um livro e do id de um capitulo do livro
     *
     * @param bookId    Id do livro que possui o capitulo que possui as notas
     * @param chapterId Id do capitulo que possui as notas
     * @return lista de NoteDTO
     */
    @Transactional(readOnly = true)
    public List<NoteDTO> listChapterNotes(Long bookId, Long chapterId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("No book with such id found"));

        Chapter chapter = book.getChapters().stream()
                .filter(c -> c.getId().equals(chapterId)).findFirst().orElseThrow(
                        () -> new IllegalArgumentException("No chapter with such id found")
                );
        return chapter.getNotes().stream().map(NoteDTO::new).toList();

    }

}
