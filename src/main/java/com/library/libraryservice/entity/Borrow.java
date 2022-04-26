package com.library.libraryservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Borrow")
public class Borrow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long borrowID;
    @Column(name = "bookID")
    private Long bookID;
    @Column(name = "userName")
    private String userName;
    @Column(name = "borrowDate")
    private LocalDateTime borrowDate;

    public Borrow() {
    }

    public Long getBorrowID() {
        return borrowID;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
}
