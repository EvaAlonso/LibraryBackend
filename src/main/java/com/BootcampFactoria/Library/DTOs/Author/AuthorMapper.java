package com.BootcampFactoria.Library.DTOs.Author;

import com.BootcampFactoria.Library.model.Author;

public class AuthorMapper {

    public static Author AuthorDTOToEntity(AuthorDTO authorDTO) {
         return new Author(
                authorDTO.name(),
                authorDTO.biography()

        );
    }

    public static AuthorDTO authorEntityToDTO(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getBiography()
        );
    }

}
