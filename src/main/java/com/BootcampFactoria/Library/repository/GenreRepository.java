package com.BootcampFactoria.Library.repository;

import com.BootcampFactoria.Library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByTitle(String title);
}
