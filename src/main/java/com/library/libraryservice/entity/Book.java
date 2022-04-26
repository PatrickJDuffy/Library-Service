package com.library.libraryservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Book")
public class Book{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bookID;
    @Column(name="title")
    private String title;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "status")
    private Status status;
    @ManyToMany(cascade={ CascadeType.MERGE})
    @JoinTable(name="author_book", joinColumns=@JoinColumn(name="book_id"), inverseJoinColumns=@JoinColumn(name="author_id"))
    @Column(name = "authors")
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, String isbn, Status status, List<Author> authors) {
        this.bookID = bookID;
        this.title = title;
        this.isbn = isbn;
        this.status = status;
        this.authors = authors;

    }

    //Getters + Setters
    public Long getBookID()
    {
        return bookID;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }
    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBookID().equals(book.getBookID()) && getTitle().equals(book.getTitle()) && getIsbn().equals(book.getIsbn()) && getStatus() == book.getStatus() && getAuthors().equals(book.getAuthors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookID(), getTitle(), getIsbn(), getStatus(), getAuthors());
    }
}
