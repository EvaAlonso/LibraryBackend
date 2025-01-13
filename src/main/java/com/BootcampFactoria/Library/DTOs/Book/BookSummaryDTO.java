package com.BootcampFactoria.Library.DTOs.Book;

import com.BootcampFactoria.Library.DTOs.Author.AuthorDTO;
import com.BootcampFactoria.Library.DTOs.Genre.GenreDTO;

import java.util.List;

public record BookSummaryDTO(
    String title,
    List<AuthorDTO> authorsName,
    String isbn,
    String cover_url,
    List<GenreDTO> genresName
) {}
