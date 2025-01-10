package com.BootcampFactoria.Library.DTOs.Book;

import com.BootcampFactoria.Library.DTOs.Author.AuthorDTO;
import com.BootcampFactoria.Library.DTOs.Genre.GenreDTO;

import java.util.List;

public record BookDetailsDTO(
        String title,
        List<AuthorDTO> authors,
        String isbn,
        String description,
        List<GenreDTO> genres
) {}
