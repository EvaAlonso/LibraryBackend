package com.BootcampFactoria.Library.DTOs.Genre;

import com.BootcampFactoria.Library.model.Genre;

public class GenreMapper {
    public static Genre genreDTOToEntity(GenreDTO genreDTO){
        return new Genre(genreDTO.name());
    }
    public static GenreDTO genreEntityToDTO(Genre genre){
        return new GenreDTO(genre.getName());
    }
}
