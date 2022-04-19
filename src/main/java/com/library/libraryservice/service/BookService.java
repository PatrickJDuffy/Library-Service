package com.library.libraryservice.service;


import com.library.libraryservice.repository.BookRepository;
import com.library.libraryservice.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        log.info("Inside saveBook method of BookService Class");
        return bookRepository.save(book);
    }

    public Book findBookByID(Long bookID) {
        log.info("Inside findBookByID method of BookService class");
        return bookRepository.findByBookID(bookID);
    }
}
