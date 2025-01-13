package com.BootcampFactoria.Library.DTOs.Author;

import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.model.Book;

import java.util.List;

public class AuthorMapper {

    public static Author toEntity(AuthorDTO authorDTORequest, List<Book> author_books) {
        return new Author(
                authorDTORequest.name(),
                authorDTORequest.biography(),
                author_books
        );
    }

    public static AuthorDTO toDTORequest(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getBiography()
        );
    }

    public static AuthorDTO toDTOResponse(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getBiography()
        );
    }

}
