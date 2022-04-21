package com.library.libraryservice.service;


import com.library.libraryservice.entity.Author;
import com.library.libraryservice.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service which retrieves and saves data for the control class
@Service
@Slf4j
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    //Saves author object to DB through the JPA RepositoryInterface
    public Author saveAuthor(Author author) {
        log.info("Inside saveAuthor method of AuthorService Class");
        return authorRepository.save(author);
    }

    //Takes id parameter and retrieves it from repository interface
    public Author findAuthorByID(Long authorID) {
        log.info("Inside findAuthorByID method of AuthorService class");
        return authorRepository.findByAuthorID(authorID);
    }

    //Returns all authors as a List
    public List<Author> getAuthors() {
        log.info("Inside getAuthors method of AuthorService class");
        return authorRepository.findAll();
    }
}
