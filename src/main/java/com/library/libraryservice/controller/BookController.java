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
    @GetMapping("/byTitle={title}")
    public BookMetaData findBookByTitle(@PathVariable("title") String title){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByTitle(title);
    }
    @GetMapping("/byIsbn={isbn}")
    public BookMetaData findBookByIsbn(@PathVariable("isbn") String isbn){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByIsbn(isbn);
    }

    @GetMapping("/byAuthor={author}")
    public List<BookMetaData> findBookByAuthor(@PathVariable("author") String author){
        log.info("Inside findBookByID method of BookController class");
        return bookService.findBookByAuthor(author);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookPartially(@PathVariable Long id, @RequestBody Book change) {

        try {
            Book book = bookService.findBookByID(id);
            book.setStatus(change.getStatus());
            return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
