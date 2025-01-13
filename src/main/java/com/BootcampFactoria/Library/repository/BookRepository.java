package com.BootcampFactoria.Library.repository;

import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthors_Id(int authorId);
    List<Book> findByGenres_Id(int genreId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthors(Optional<Author> author);
    List<Book> findByGenre(Optional<Genre> genre);
    Boolean existsByIsbn(String isbn);
}
