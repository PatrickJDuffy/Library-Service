package com.library.libraryservice.controller;


import com.library.libraryservice.entity.Author;
import com.library.libraryservice.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/")
    public Author saveAccount(@RequestBody Author author){
        log.info("Inside saveAuthor method of AuthorController Class");
        return authorService.saveAuthor(author);
    }

    @GetMapping("/{id}")
    public Author findAuthorByID(@PathVariable("id") Long authorID){
        log.info("Inside findAuthorByID method of AuthorController class");
        return authorService.findAuthorByID(authorID);
    }

    @GetMapping("/")
    public List<Author> getAuthors(){
        log.info("Inside getAuthors method of AuthorController class");
        return authorService.getAuthors();
    }
}