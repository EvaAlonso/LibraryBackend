package com.BootcampFactoria.Library.controller;

import com.BootcampFactoria.Library.DTOs.Book.BookDetailsDTO;
import com.BootcampFactoria.Library.DTOs.Book.BookMapper;
import com.BootcampFactoria.Library.DTOs.Book.BookSummaryDTO;
import com.BootcampFactoria.Library.DTOs.ErrorDTO;
import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Genre;
import com.BootcampFactoria.Library.service.AuthorService;
import com.BootcampFactoria.Library.service.BookService;
import com.BootcampFactoria.Library.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<BookSummaryDTO>> getAllBooks() {
        //busca libros y convierte entidades a DTO
        List<BookSummaryDTO> books = bookService.getAll().stream()
                .map(book -> BookMapper.toSummaryDTO(book))
                .collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable int id) {
        //comprueba si existe libro
        Optional<Book> bookOptional = bookService.findBook(id);
        if (bookOptional.isPresent()) {
            //convierte entidad en DTO
            BookDetailsDTO bookDetails = BookMapper.toDetailsDTO(bookOptional.get());
            return new ResponseEntity<>(bookDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDetailsDTO book) {
        try {
            //comprueba si ISBN ya existe
            if (bookService.existsByIsbn(book.isbn())) {
                throw new IllegalArgumentException("El ISBN ya existe.");
            }
            //comprueba si existen autores y si no, los crea
            List<Author> authors = book.authors().stream()
                    .map(authorDTO -> authorService.findAuthorByName(authorDTO.name())
                            .orElseGet(() -> authorService.addAuthor(new Author(authorDTO.name()))))
                    .collect(Collectors.toList());
            //comprueba si existen los géneros y si no, los crea
            List<Genre> genres = book.genres().stream()
                    .map(genreDTO -> genreService.findGenreByName(genreDTO.name())
                            .orElseGet(() -> genreService.addGenre(new Genre(genreDTO.name()))))
                    .collect(Collectors.toList());
            //convierte el DTO en entidad Book
            Book newBook = BookMapper.toEntity(book, authors, genres);
            Book savedBook = bookService.addBook(newBook);
            return new ResponseEntity<>(BookMapper.toDetailsDTO(savedBook), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErrorDTO errorResponse = new ErrorDTO("BAD_REQUEST", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> updateBook(@PathVariable int id, @RequestBody BookDetailsDTO book) {
        //búsqueda del libro
        Optional<Book> existingBook = bookService.findBook(id);
        if (existingBook.isPresent()) {
            //comprueba si existen autores y si no, los crea
            List<Author> authors = book.authors().stream()
                    .map(authorDTO -> authorService.findAuthorByName(authorDTO.name()).orElseThrow())
                    .collect(Collectors.toList());

            //comprueba si existen los géneros y si no, los crea
            List<Genre> genres = book.genres().stream()
                    .map(genreDTO -> genreService.findGenreByName(genreDTO.name()).orElseThrow())
                    .collect(Collectors.toList());
            //convierte el DTO en entidad Book
            Book updatedBook = BookMapper.toEntity(book, authors, genres);
            updatedBook.setId(id);
            Book savedBook = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(BookMapper.toDetailsDTO(savedBook), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (bookService.findBook(id).isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
