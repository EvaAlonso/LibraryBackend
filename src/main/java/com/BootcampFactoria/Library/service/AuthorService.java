package com.BootcampFactoria.Library.service;

import com.BootcampFactoria.Library.model.Author;
import com.BootcampFactoria.Library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}
