package com.library.libraryservice.controller;

import com.library.libraryservice.entity.Book;
import com.library.libraryservice.entity.BookMetaData;
import com.library.libraryservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//BookController to deal with CRUD operations
@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    //Retrieves Book object from request body, sends it to the service to persist
    @PostMapping("/")
    public Book saveBook(@RequestBody Book book ){
        log.info("Inside saveBook method of BookController Class");
        return bookService.saveBook(book);
    }

    //Retrieves id from path and the service retrieves the Book by the ID
    @GetMapping("/{id}")
    public Book findBookByID(@PathVariable("id") Long bookID){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByID(bookID);
    }
    //Retrieves isbn from path and the service retrieves the Book by the isbn
    @GetMapping("/byIsbn={isbn}")
    public BookMetaData findBookByIsbn(@PathVariable("isbn") String isbn){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByIsbn(isbn);
    }
    //Retrieves title from path and the service retrieves the Books with the same title
    @GetMapping("/byTitle={title}")
    public List<BookMetaData> findBookByTitle(@PathVariable("title") String title){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByTitle(title);
    }
    //Retrieves author name from path and the service retrieves the Books by that author
    @GetMapping("/byAuthor={author}")
    public List<BookMetaData> findBookByAuthor(@PathVariable("author") String author){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByAuthor(author);
    }

    //Retrieves isbn from path and the service retrieves the Book by the isbn
    @PatchMapping("/borrow={id}")
    public ResponseEntity<Book> updateStatusBorrowed(@PathVariable Long id, @RequestBody Book change) {
        try {
            return new ResponseEntity<Book>(bookService.UpdateStatus(id, change.getStatus()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
