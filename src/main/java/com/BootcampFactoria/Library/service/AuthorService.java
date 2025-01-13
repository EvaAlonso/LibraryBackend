package com.BootcampFactoria.Library.service;

import com.BootcampFactoria.Library.exception.ObjectNotFoundException;
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

    public Author addAuthor(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author updatedAuthor(int id, Author updateAuthor){
        Optional<Author> foundAuthor = authorRepository.findById(id);

        if(foundAuthor.isPresent()){
            Author existingAuthor = foundAuthor.get();
            existingAuthor.setName(updateAuthor.getName());
            existingAuthor.setBiography(updateAuthor.getBiography());

            return authorRepository.save(existingAuthor);
        }
        throw new ObjectNotFoundException("Author", id);
    }
    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
}
