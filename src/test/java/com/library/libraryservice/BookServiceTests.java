package com.library.libraryservice;

import com.library.libraryservice.entity.Author;
import com.library.libraryservice.entity.Book;
import com.library.libraryservice.entity.BookMetaData;
import com.library.libraryservice.entity.Status;
import com.library.libraryservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookService bookService = new BookService();

    //Tests the UniqueBooks method of Service layer
    @Test
    public void getUniqueBooks(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Patrick"));
        Book book = new Book(1L,"HowToRead", "123456", Status.AVAILABLE, authors);
        Book book2 = new Book(2L,"HowToRead", "123456", Status.AVAILABLE, authors);
        Book book3 = new Book(3L,"HowToRead", "1234567", Status.AVAILABLE, authors);
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        List<BookMetaData> meta = new ArrayList<>();
        List<Long> avail = new ArrayList<>();
        List<Long> borrow = new ArrayList<>();
        avail.add(1L);
        avail.add(2L);
        List<Long> avail2 = new ArrayList<>();
        avail2.add(3L);
        meta.add(new BookMetaData("HowToRead", "1234567", 1, 0, avail2, borrow, authors));
        meta.add(new BookMetaData("HowToRead", "123456", 2, 0, avail, borrow, authors));

        List<BookMetaData> result = bookService.getUniqueBooks(books);

        assertTrue(meta.equals(result));
    }
}
