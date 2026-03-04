package com.david.bookshelf.repositories;

import com.david.bookshelf.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, Long> {

    //FIXME: EM TESTE
    @Query("SELECT book FROM Book book JOIN FETCH book.chapters WHERE book.id = :bookId ")
    Book findBookWithChapters(Long bookId);
}