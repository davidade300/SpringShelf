package com.david.bookshelf.services;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.book.BookRequest;
import com.david.bookshelf.dtos.book.BookUpdate;
import com.david.bookshelf.dtos.book.BookWithChapters;
import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.repositories.BookRepository;
import com.david.bookshelf.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * rtorna uma lista de BookDTO
     *
     * @return ListBookDTO
     */
    @Transactional(readOnly = true)
    public Page<BookDTO> findAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(BookDTO::new);
    }

    /**
     * Retorna um livro a partir de seu id
     *
     * @param id id do livro a ser encontrado
     * @return objeto do tipo BookDTO
     */
    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id) {
        Book book = loadBookByIdOrThrow(id);

        return new BookDTO(book);
    }

    /**
     * FIXME: EM TESTE
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BookWithChapters findBookWithChapters(Long id) {
        Book book = bookRepository.findBookWithChapters(id);

        return new BookWithChapters(book);


    }

    /**
     * Cria um novo book a partir de um BookRequest
     *
     * @param bookRequest BookRequest contendo as informacoes para criar um novo book
     * @return BookDTO com as informaçcoes do novo book
     */
    @Transactional
    public BookDTO insert(BookRequest bookRequest) {
        Book book = new Book();
        copyBookRequestToEntity(bookRequest, book);
        bookRepository.save(book);

        return new BookDTO(book);
    }

    /**
     * Atualiza um book com os dados de um DTO.
     * FIXME: atualmente(23/02/2026) o método aceita campos vázios
     *  análisar se o front deve garantir que o objeto venha
     *  completo (a pesar de validacoes no back-end)
     *
     * @param id         id do book a ser atualizado
     * @param bookUpdate DTO com os dados para atualizar o book
     * @return DTO do book com os dados atualizado
     */
    @Transactional
    public BookDTO update(Long id, BookUpdate bookUpdate) {
        Book book = loadBookByIdOrThrow(id);

        book.updateDescription(bookUpdate.getDescription());
        bookRepository.save(book);

        return new BookDTO(book);
    }


    /**
     * Deleta um book a partir do id
     *
     * @param id id do book a ser deletado
     */
    @Transactional
    public void delete(Long id) {
        Book book = loadBookByIdOrThrow(id);

        bookRepository.deleteById(id);
    }

    /**
     * TODO: Verificar se realmente nao ha nenhum uso para esse metodo
     * Copia um BookDto para um Book, só pra nao deixar os metodos principai muito verbosos meso
     *
     * @param dto  Dto com os dados a serem passados para entidade
     * @param book Entidade a receber os dados
     */
    private void copyDtoToEntity(BookDTO dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setVersion(dto.getVersion());
        book.setReleaseDate(dto.getReleaseDate());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setIsbn10(dto.getIsbn10());
        book.setIsbn13(dto.getIsbn13());
        book.setCoverImgUrl(dto.getCoverImgUrl());
        book.setDescription(dto.getDescription());
        book.setLastUpdatedAt(dto.getLastUpdatedAt());
    }

    /**
     * Copia um BookRequest para um Book, só pra nao deixar os metodos principai muito verbosos meso
     *
     * @param bookRequest objeto com os campos para criacao de um Book
     * @param book        objeto Book que recebe os dados a serem persistidos
     */
    private void copyBookRequestToEntity(BookRequest bookRequest, Book book) {
        book.setTitle(bookRequest.getTitle());
        book.setVersion(bookRequest.getVersion());
        book.setReleaseDate(bookRequest.getReleaseDate());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setIsbn10(bookRequest.getIsbn10());
        book.setIsbn13(bookRequest.getIsbn13());
        book.setCoverImgUrl(bookRequest.getCoverImgUrl());
        book.setDescription(bookRequest.getDescription());
        book.setLastUpdatedAt(LocalDateTime.now());
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


// Mover para o chapterService \/


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
                () -> new IllegalArgumentException("Book with id " + bookId + " not found"));

        Chapter chapter = book.getChapters().stream()
                .filter(c -> c.getId().equals(chapterId)).findFirst().orElseThrow(
                        () -> new IllegalArgumentException("No chapter with such id found")
                );

        return chapter.getNotes().stream().map(NoteDTO::new).toList();
    }

}
