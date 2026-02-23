package com.david.bookshelf.services;

import com.david.bookshelf.dtos.book.BookDTO;
import com.david.bookshelf.dtos.chapter.ChapterDTO;
import com.david.bookshelf.dtos.note.NoteDTO;
import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import com.david.bookshelf.repositories.BookRepository;
import com.david.bookshelf.services.exceptions.ResouceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * Cria um novo book a partir de um BookDTO
     *
     * @param bookDTO Dto contendo as informacoes para criar um novo book
     * @return BookDTO com as informaçcoes do novo book
     */
    @Transactional
    public BookDTO insert(BookDTO bookDTO) {
        Book book = new Book();
        copyDtoToEntity(bookDTO, book);
        bookRepository.save(book);

        return new BookDTO(book);
    }

    /**
     * Atualiza um book com os dados de um DTO.
     * FIXME: atualmente(23/02/2026) o método aceita campos vázios
     *  análisar se o front deve garantir que o objeto venha
     *  completo (a pesar de validacoes no back-end)
     *
     * @param id  id do book a ser atualizado
     * @param dto DTO com os dados para atualizar o book
     * @return DTO do book com os dados atualizado
     */
    @Transactional
    public BookDTO update(Long id, BookDTO dto) {
        Book book = loadBookByIdOrThrow(id);

        copyDtoToEntity(dto, book);
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
     * Copia um BookDto para um Book
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
     * Funcao para reduzir a repeticao de "Book book = bookRepository..."
     * e para validacao em outros services já que a entidade Book e a raiz de um
     * aggregate
     *
     * @param id id do book a ser retornado
     * @return Book
     */
    protected Book loadBookByIdOrThrow(Long id) {

        return bookRepository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Book with id " + id + " not found")
        );
    }


// Mover para o chapterService \/

    /**
     * retorna uma lista de ChapterDTO para o controller
     * TODO: Adicionar sorting no streaming para ter uma lista ordenada (createdAt)
     *
     * @param id id do livro que se quer consulta os capitulos
     * @return lista de ChapterDTO
     */
    @Transactional(readOnly = true)
    public List<ChapterDTO> listBookChapters(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Book with id " + id + " not found"));

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
                () -> new IllegalArgumentException("Book with id " + bookId + " not found"));

        Chapter chapter = book.getChapters().stream()
                .filter(c -> c.getId().equals(chapterId)).findFirst().orElseThrow(
                        () -> new IllegalArgumentException("No chapter with such id found")
                );

        return chapter.getNotes().stream().map(NoteDTO::new).toList();
    }

}
