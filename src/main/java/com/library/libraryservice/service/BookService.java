package com.library.libraryservice.service;

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

//Service that uses the repository to retrieve and upload data to JPA
//Where the underlying operations are done to the data before and after using the database
@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //Sends the Book object to the JPA through the Repository to be persisted
    public Book saveBook(Book book) {
        log.info("Inside saveBook method of BookService Class");
        return bookRepository.save(book);
    }

    //Retrieves Book Object from JPA using the bookID parameter
    public Book findBookByID(Long bookID) {
        log.info("Inside findBookByID method of BookService class");
        return bookRepository.findByBookID(bookID);
    }

    //Retrieves BookMetaData object of specific book, querying the DB on the isbn parameter
    public BookMetaData findBookByIsbn(String isbn) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        List<Book> books = bookRepository.findByTitleIgnoreCase(isbn);
        List<Long> availableBooks = new ArrayList<>();
        List<Long> borrowedBooks = new ArrayList<>();
        String title;

        try{
            title = books.get(0).getTitle();
            for (Book b : books) {
                //Counting all copies and also the number of available copies
                if (b.getStatus() == Status.AVAILABLE) {
                    availableBooks.add(b.getBookID());
                } else {
                    borrowedBooks.add(b.getBookID());
                }

            }
        }catch(Exception e){
            List<Author> authors = new ArrayList<Author>();
            return new BookMetaData("Not Found", isbn, 0, 0, availableBooks, borrowedBooks, authors);
        }

        List<Author> authors = books.get(0).getAuthors();
        //Generates metadata of the book searched, with information about stock.
        BookMetaData summary = new BookMetaData(title, isbn, availableBooks.size(), borrowedBooks.size(),
                availableBooks, borrowedBooks, authors);
        return summary;
    }

    public List<BookMetaData> findBookByTitle(String title) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        Hashtable<String, BookMetaData> uniqueBooks = new Hashtable<>();
        List<Book> books = bookRepository.findByTitleIgnoreCase(title);
        if (books.isEmpty()) {
            //Return message when book is not found
            System.out.println("Unable to find : " + title);
            List<Author> authors = new ArrayList<Author>();
            List<Long> lists = new ArrayList<Long>();
            Collections.singletonList(new BookMetaData("Not Found", "Not Found", 0, 0, lists, lists, authors));
             }

        //Generates metadata of the books searched with information about stock.
        return getUniqueBooks(books);
    }



    public List<BookMetaData> findBookByAuthor(String author) {
        log.info("Inside findBookByTitle method of BookService class");
        //Find instances of book titled with the input parameter
        List<Book> books = bookRepository.findByAuthors_Name(author);
        if(books.isEmpty()) {
            //Return console message when book is not found
            System.out.println("Unable to find books by : " + author);
            List<Author> authors = new ArrayList<Author>();
            List<Long> lists = new ArrayList<Long>();
            return Collections.singletonList(new BookMetaData("Not Found", "Not Found", 0, 0, lists, lists, authors));
        }

        //Calls the BookService.getUniqueBooks() Method and returns the Values of the resulting Hash
        return getUniqueBooks(books);
    }

    public List<BookMetaData> getUniqueBooks(List<Book> books){
        //Using hash table to store unique values and update their inner variables
        Hashtable<String, BookMetaData> uniqueBooks = new Hashtable<String, BookMetaData>();
        for (Book b : books) {
            //Each books isbn queries the hashtable to find if its BookMetaData Object is stored already
            String isbn = b.getIsbn();
            //If the hashtable contains the isbn key the value is updated by incrementing the list of
            //either the borrowed or available bookID lists, inside the BookMetaData Object
            if(uniqueBooks.containsKey(isbn)){
                //TODO - create method to share this funtionality with get by author
                BookMetaData book = uniqueBooks.get(isbn);
                if(b.getStatus() == Status.AVAILABLE){
                    book.setNoOfAvailable(book.getNoOfAvailable() + 1);
                    book.addAvailableBookID(b.getBookID());
                }
                else{
                    book.setNoOfBorrowed((book.getNoOfBorrowed() + 1));
                    book.addBorrowedBookID(b.getBookID());
                }
                //HashTable is updated and the loop is continued;
                uniqueBooks.put(isbn, book);
                continue;
            }

            //If the isbn key is not in the hashtable a new key value pair is stored in it
            List<Long> availableBooks = new ArrayList<>();
            List<Long> borrowedBooks = new ArrayList<>();
            List<Author> authors = b.getAuthors();
            //Storing bookIDs of Borrowed and Available books
            if (b.getStatus() == Status.AVAILABLE) {
                availableBooks.add(b.getBookID());
            } else {
                borrowedBooks.add(b.getBookID());
            }

            //HastTable is updated with new BookMetaData Object for corresponding book isbn
            uniqueBooks.put(isbn, new BookMetaData(b.getTitle(), isbn, availableBooks.size(), borrowedBooks.size(), availableBooks, borrowedBooks, authors));
        }
        //Returns ArrayList of BookMetaData objects for unique
        return new ArrayList<BookMetaData>(uniqueBooks.values());
    }

    public Book UpdateStatus(Long id, Status status) {
        Book book = bookRepository.findByBookID(id);
        book.setStatus(status);
        return bookRepository.save(book);
    }
}
