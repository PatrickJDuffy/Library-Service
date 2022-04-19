package com.library.libraryservice.service;


import com.library.libraryservice.entity.Author;
import com.library.libraryservice.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        log.info("Inside saveAuthor method of AuthorService Class");
        return authorRepository.save(author);
    }

    public Author findAuthorByID(Long authorID) {
        log.info("Inside findAuthorByID method of AuthorService class");
        return authorRepository.findByAuthorID(authorID);
    }

    public List<Author> getAuthors() {
        log.info("Inside getAuthors method of AuthorService class");
        return authorRepository.findAll();
    }
}
