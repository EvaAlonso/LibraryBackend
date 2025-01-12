package com.BootcampFactoria.Library.controller;



import com.BootcampFactoria.Library.DTOs.Genre.GenreDTO;
import com.BootcampFactoria.Library.DTOs.Genre.GenreMapper;
import com.BootcampFactoria.Library.exception.ObjectNotFoundException;
import com.BootcampFactoria.Library.model.Genre;
import com.BootcampFactoria.Library.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/genres")
@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;

    }
    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.getAll();
    }
    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO){
        Genre newGenre = GenreMapper.genreDTOToEntity(genreDTO);
        Genre createdGenre = genreService.addGenre(newGenre);
        GenreDTO createdGenreDTO = GenreMapper.genreEntityToDTO(createdGenre);
        return new ResponseEntity<>(createdGenreDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable int id){
        genreService.deleteGenre(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Genre> findGenreById(@PathVariable int id){
        Optional<Genre> foundGenre = genreService.findGenre(id);
        if(foundGenre.isPresent()){
            return new ResponseEntity<>(foundGenre.get(), HttpStatus.FOUND);
        }
        throw new ObjectNotFoundException("Genre", id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id, @RequestBody Genre updatedGenre){
        try {
            Genre genre = genreService.updateGenre(id, updatedGenre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
