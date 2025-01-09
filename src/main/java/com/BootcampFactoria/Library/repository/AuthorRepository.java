package com.BootcampFactoria.Library.repository;

import com.BootcampFactoria.Library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
