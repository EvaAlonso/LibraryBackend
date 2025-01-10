package com.BootcampFactoria.Library.repository;

import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthors(Author author);
    List<Book> findByGenres(Genre genre);

}