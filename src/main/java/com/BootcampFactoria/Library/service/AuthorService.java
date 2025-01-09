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

    public AuthorService(AuthorRepository AuthorRepository){
        this.authorRepository = AuthorRepository;
    }
    public List<Author> getAll(){
        return  authorRepository.findAll();
    }
    public Author addAuthor(Author newAuthor){
        return authorRepository.save(newAuthor);
    }
    public void deleteAuthor(int id){
        authorRepository.deleteById(id);
    }
    public Optional<Author> findAuthor(int id){
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if (foundAuthor.isPresent()){
            return authorRepository.findById(id);
        }
        throw new ObjectNotFoundException("Author", id);
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
}
