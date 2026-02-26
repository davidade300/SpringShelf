package com.david.bookshelf.repositories;

import com.david.bookshelf.entities.Book;
import com.david.bookshelf.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    //FIXME: EM TESTE
    @Query("SELECT book FROM Book book JOIN FETCH book.chapters")
    Book findBookWithChapters(Long bookId);
}