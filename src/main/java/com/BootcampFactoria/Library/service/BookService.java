package com.BootcampFactoria.Library.service;



import com.BootcampFactoria.Library.exception.*;
import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Genre;
import com.BootcampFactoria.Library.repository.AuthorRepository;
import com.BootcampFactoria.Library.repository.BookRepository;
import com.BootcampFactoria.Library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }


    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public Book addBook(Book newBook){
        return bookRepository.save(newBook);
    }
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
    public Optional<Book> findBook(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()){
            return bookRepository.findById(id);
        } throw new ObjectNotFoundException("Book", id);
    }
    public Book updatedBook(int id, Book updatedBook){

        Optional<Book> foundBook = bookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook = foundBook.get();

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());

            return bookRepository.save(existingBook);
        }
        throw new ObjectNotFoundException("Book", id);
    }
    public Optional<Book> findBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(book.isEmpty()){
            throw new IsbnNotFoundException(isbn);
        }
        return book;
    }
    public Optional<Book> findBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isEmpty()){
            throw new TitleNotFoundException(title);
        }
        return book;
    }

    public List<Book> findGenreByName(String name) {
        Optional<Genre> genre = genreRepository.findByName(name);
        if (genre.isPresent()){
            return bookRepository.findByGenres(genre);
        }
        throw new GenreNotFoundException(name);
    }
    public List<Book> findAuthorByName(String name) {
        Optional<Author> author = authorRepository.findByName(name);
        if(author.isPresent()){
            return bookRepository.findByAuthors(author.get());
        }
        throw new AuthorNotFoundException(name);
    }
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);  // Verifica en el repositorio si el ISBN existe
    }


}
