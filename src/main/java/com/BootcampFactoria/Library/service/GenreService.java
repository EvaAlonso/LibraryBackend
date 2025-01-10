package com.BootcampFactoria.Library.service;


import com.BootcampFactoria.Library.model.Genre;
import com.BootcampFactoria.Library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }
    public List<Genre> getAll(){
        return genreRepository.findAll();
    }
    public Genre addGenre(Genre newGenre){
        return genreRepository.save(newGenre);
    }
    public void deleteGenre(int id){
        genreRepository.deleteById(id);
    }
    public Optional<Genre> findGenre(int id){
        return genreRepository.findById(id);
    }
    public Genre updateGenre(int id, Genre updateGenre){
        Optional<Genre> foundGenre = genreRepository.findById(id);

        if(foundGenre.isPresent()){
            Genre existingGenre = foundGenre.get();

            existingGenre.setTitle(updateGenre.getTitle());

            return genreRepository.save(existingGenre);
        }
        throw new RuntimeException("Genre not found with id: " + id);
    }

}
