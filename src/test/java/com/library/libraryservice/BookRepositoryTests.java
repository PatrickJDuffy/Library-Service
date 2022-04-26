package com.library.libraryservice;

import com.library.libraryservice.entity.Author;
import com.library.libraryservice.entity.Book;
import com.library.libraryservice.entity.Status;
import com.library.libraryservice.repository.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    BookRepository repository;

    @Test
    public void should_find_no_books_if_repository_is_empty() {
        Iterable books = repository.findAll();
        assertThat(books).isEmpty();
    }

    @Test
    public void should_store_a_book() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Patrick"));
        Book book = new Book("HowToRead", "123456", Status.AVAILABLE, authors);
        Book result = repository.save(book);
        assertThat(result).hasFieldOrPropertyWithValue("title", "HowToRead");
        assertThat(result).hasFieldOrPropertyWithValue("isbn", "123456");
        assertThat(result).hasFieldOrPropertyWithValue("bookID", book.getBookID());
    }

    @Test
    public void should_find_all_books() {
        Status status = Status.AVAILABLE;
        Author author = new Author("Patrick");
        entityManager.persist(author);
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        Book book = new Book("HowToRead", "123456", Status.AVAILABLE, authors);
        Book book2 = new Book("HowToRead2", "1234567", Status.AVAILABLE, authors);
        entityManager.persist(book);
        entityManager.persist(book2);
        Iterable books = repository.findAll();
        assertThat(books).hasSize(2).contains(book);
    }

    @Test
    public void should_find_book_by_id() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Patrick");
        entityManager.persist(author);
        authors.add(author);
        Book book = new Book("HowToRead", "123456", Status.AVAILABLE, authors);
        entityManager.persist(book);
        Book book2 = new Book("HowToRead2", "1234567", Status.AVAILABLE, authors);
        entityManager.persist(book2);
        Book result = repository.findById(book.getBookID()).get();
        assertThat(result).isEqualTo(book);
    }

    @Test
    public void should_find_books_by_Author() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Patrick");
        entityManager.persist(author);
        authors.add(author);
        Book book = new Book("HowToRead", "123456", Status.AVAILABLE, authors);
        Book book2 = new Book("HowToRead2", "1234567", Status.AVAILABLE, authors);
        entityManager.persist(book);
        entityManager.persist(book2);
        Iterable books = repository.findByAuthors_Name("PatrIck");
        assertThat(books).hasSize(2).contains(book, book2);
    }
    @Test
    public void should_find_books_by_title() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Patrick");
        entityManager.persist(author);
        authors.add(author);
        Book book = new Book("HowToRead", "123456", Status.AVAILABLE, authors);
        Book book2 = new Book("HowToRead", "1234567", Status.AVAILABLE, authors);
        Book book3 = new Book("HowTo", "12345674", Status.AVAILABLE, authors);
        entityManager.persist(book);
        entityManager.persist(book2);
        entityManager.persist(book3);
        Iterable books = repository.findByTitleIgnoreCase("HowToRead");
        assertThat(books).hasSize(2).contains(book, book2);
    }
}
