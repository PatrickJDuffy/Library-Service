package com.library.libraryservice.controller;

import com.library.libraryservice.entity.Book;
import com.library.libraryservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book saveBook(@RequestBody Book book ){
        log.info("Inside saveBook method of BookController Class");
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book findBookByID(@PathVariable("id") Long bookID){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByID(bookID);
    }
}
