package com.BootcampFactoria.Library.DTOs.Book;

import com.BootcampFactoria.Library.DTOs.Author.AuthorDTO;
import com.BootcampFactoria.Library.DTOs.Genre.GenreDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record BookDetailsDTO(
        @NotEmpty(message = "Title is required.")
        @Size(min = 2, max = 20, message = "Title must be at least 2 characters and max 20 characters.")
        String title,
        @NotEmpty(message = "At least one author is required.")
        List<AuthorDTO> authors,
        @NotEmpty(message = "ISBN is required.")
        @Size(min = 13, max = 13, message = "ISBN must be 13 characters.")
        String isbn,
        String cover_url,
        String description,
        List<GenreDTO> genres
) {}
