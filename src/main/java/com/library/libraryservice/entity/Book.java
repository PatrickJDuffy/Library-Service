package com.library.libraryservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bookID;
    @Column(name="title")
    private String title;
    private String isbn;
    private Status status;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="author_book", joinColumns=@JoinColumn(name="book_id"), inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author> authors;


    //Getters + Setters
    public Long getBookID()
    {
        return bookID;
    }
    public void setBookID(Long bookID)
    {
        this.bookID = bookID;
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
}
