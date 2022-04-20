package com.library.libraryservice.service;
//TODO - Re do metadata class to accept no of borrowed instead of total number of copies, keep it consistant throughout
//TODO - Re-do getbyauthor , make sure to separate by unique isbns instead of titles

import com.library.libraryservice.entity.Author;
import com.library.libraryservice.entity.BookMetaData;
import com.library.libraryservice.entity.Status;
import com.library.libraryservice.repository.BookRepository;
import com.library.libraryservice.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

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

    public BookMetaData findBookByTitle(String title) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        List<Book> books = bookRepository.findByTitleIgnoreCase(title);
        if (books.isEmpty()) {
            //Return message when book is not found
            System.out.println("Unable to find : " + title);
            List<Author> authors = new ArrayList<Author>();
            List<Long> lists = new ArrayList<Long>();
            return new BookMetaData(title, "Not Found", 0, 0, lists, lists, authors);
        }
        String isbn = books.get(0).getIsbn();
        List<Long> availableBooks = new ArrayList<>();
        List<Long> borrowedBooks = new ArrayList<>();
        for (Book b : books) {
            //Counting all copies and also the number of available copies
            if (b.getStatus() == Status.AVAILABLE) {
                availableBooks.add(b.getBookID());
            } else {
                borrowedBooks.add(b.getBookID());
            }

        }

        List<Author> authors = books.get(0).getAuthors();
        //Generates metadata of the book searched with information about stock.
        BookMetaData summary = new BookMetaData(title, isbn, availableBooks.size(), borrowedBooks.size(),
                availableBooks, borrowedBooks, authors);
        return summary;
    }

    public BookMetaData findBookByIsbn(String isbn) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        List<Book> books = bookRepository.findByTitleIgnoreCase(isbn);
        if (books.isEmpty()) {
            //Return message when book is not found
            System.out.println("Unable to find : " + isbn);
            List<Author> authors = new ArrayList<Author>();
            List<Long> lists = new ArrayList<Long>();
            return new BookMetaData(isbn, "Not Found", 0, 0, lists, lists, authors);
        }
        String title = books.get(0).getTitle();
        List<Long> availableBooks = new ArrayList<>();
        List<Long> borrowedBooks = new ArrayList<>();
        for (Book b : books) {
            //Counting all copies and also the number of available copies
            if (b.getStatus() == Status.AVAILABLE) {
                availableBooks.add(b.getBookID());
            } else {
                borrowedBooks.add(b.getBookID());
            }

        }

        List<Author> authors = books.get(0).getAuthors();
        //Generates metadata of the book searched with information about stock.
        BookMetaData summary = new BookMetaData(title, isbn, availableBooks.size(), borrowedBooks.size(),
                availableBooks, borrowedBooks, authors);
        return summary;
    }

    public List<BookMetaData> findBookByAuthor(String author) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        Hashtable<String, BookMetaData> uniqueBooks = new Hashtable<>();
        List<Book> books = bookRepository.findByAuthors_Name(author);
        if(books.isEmpty()) {
            //Return message when book is not found
            System.out.println("Unable to find books by : " + author);
            List<Author> authors = new ArrayList<Author>();
            List<Long> lists = new ArrayList<Long>();
            return Collections.singletonList(new BookMetaData("Not Found", "Not Found", 0, 0, lists, lists, authors));
        }


        for(Book b : books){
            String title = b.getTitle();
            if(uniqueBooks.containsKey(title)){
                BookMetaData book = uniqueBooks.get(title);
                if(b.getStatus() == Status.AVAILABLE){
                    book.setNoOfAvailable(book.getNoOfAvailable() + 1);
                    book.addAvailableBookID(b.getBookID());
                }
                else{
                    book.setNoOfCopies(book.getNoOfCopies() + 1);
                    book.addBorrowedBookID(b.getBookID());
                }


                uniqueBooks.put(title, book);
                continue;
            }
            String isbn = b.getIsbn();
            int available = 0;
            int borrowed = 0;
            List<Long> borrowedList = new ArrayList<>();
            List<Long> availableList = new ArrayList<>();
            if(b.getStatus()==Status.AVAILABLE) {
                available = 1;
                availableList.add(b.getBookID());
            }
            else{
                borrowed = 1;
                borrowedList.add(b.getBookID());
            }

            List<Author> authors = books.get(0).getAuthors();
            //Generates metadata of the book searched with information about stock.
            BookMetaData summary = new BookMetaData(title, isbn, available, borrowed, availableList, borrowedList, authors);
            uniqueBooks.put(title, summary);
        }

        return new ArrayList<BookMetaData>(uniqueBooks.values());
    }
}
