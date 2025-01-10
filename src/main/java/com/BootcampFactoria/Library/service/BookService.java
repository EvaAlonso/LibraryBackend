package com.BootcampFactoria.Library.service;

import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByAuthorsId(int authorsId) {
        return bookRepository.findByAuthors_Id(authorsId);
    }

    public List<Book> findByGenresId(int genreId) {
        return bookRepository.findByGenres_Id(genreId);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book create(Book newBook) {
        if (bookRepository.existsById(newBook.getId())) {
            throw new IllegalArgumentException("ISBN already exists.");
        }
        return bookRepository.save(newBook);
    }

    public Book updateBook(int id, Book bookDetails) {
        //comprobación de existencia del libro
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + id + " not found."));
        //comprobación de ISBN
        if (!existingBook.getIsbn().equals(bookDetails.getIsbn()) && bookRepository.existsByIsbn(bookDetails.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthors(bookDetails.getAuthors());
        existingBook.setDescription(bookDetails.getDescription());
        existingBook.setGenres(bookDetails.getGenres());

        return bookRepository.save(existingBook);
    }

    public String delete(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book with id " + id + " deleted successfully.";
        }
        return "Book with id " + id + " not found.";
    }

    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
