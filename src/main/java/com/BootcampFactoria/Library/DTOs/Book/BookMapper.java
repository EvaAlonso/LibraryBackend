package com.BootcampFactoria.Library.DTOs.Book;

import com.BootcampFactoria.Library.DTOs.Author.AuthorDTO;
import com.BootcampFactoria.Library.DTOs.Genre.GenreDTO;
import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;
import com.BootcampFactoria.Library.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static Book toEntity(BookDetailsDTO bookDetailsDTO, List<Author> authors, List<Genre> genres) {
        Book book = new Book();
        book.setIsbn(bookDetailsDTO.isbn());
        book.setTitle(bookDetailsDTO.title());
        book.setAuthors(authors);
        book.setGenres(genres);

        return book;
    }

    public static BookDetailsDTO toDetailsDTO(Book book) {
        return new BookDetailsDTO(
                book.getTitle(),
                book.getAuthors().stream().map(author -> new AuthorDTO(author.getName(), author.getBiography()))
                        .collect(Collectors.toList()),
                book.getIsbn(),
                book.getDescription(),
                book.getGenres().stream().map(genre -> new GenreDTO(genre.getName()))
                        .collect(Collectors.toList())
        );
    }

    public static BookSummaryDTO toSummaryDTO(Book book) {
        return new BookSummaryDTO(
                book.getTitle(),
                book.getAuthors().stream().map(author -> new AuthorDTO(author.getName(), author.getBiography()))
                        .collect(Collectors.toList()),
                book.getIsbn(),
                book.getGenres().stream().map(genre -> new GenreDTO(genre.getName()))
                        .collect(Collectors.toList())
        );
    }
}
