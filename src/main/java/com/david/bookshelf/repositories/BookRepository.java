package com.david.bookshelf.repositories;

import com.david.bookshelf.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}