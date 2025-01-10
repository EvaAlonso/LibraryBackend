package com.BootcampFactoria.Library.service;

import com.BootcampFactoria.Library.model.Genre;
import com.BootcampFactoria.Library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findGenreById(int id) {
        return genreRepository.findById(id);
    }

    public Optional<Genre> findGenreByName(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Genre> findAllGenresById(List<Integer> ids) {
        return genreRepository.findAllById(ids);
    }

    public void deleteGenreById(int id) {
        genreRepository.deleteById(id);
    }
}
