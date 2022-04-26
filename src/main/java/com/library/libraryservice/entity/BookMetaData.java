package com.library.libraryservice.entity;

import java.util.List;
import java.util.Objects;

//Holds the metadata of books,
public class BookMetaData {
    private String title;
    private String isbn;
    private int noOfAvailable;
    private int noOfBorrowed;
    private List<Long> availableCopies;
    private List<Long> borrowedCopies;
    private List<Author> authors;

    public BookMetaData(String title, String isbn, int noOfAvailable, int noOfBorrowed, List<Long> availableCopies, List<Long> borrowedCopies, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.noOfAvailable = noOfAvailable;
        this.noOfBorrowed = noOfBorrowed;
        this.availableCopies = availableCopies;
        this.borrowedCopies = borrowedCopies;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void addAvailableBookID(Long id){
        availableCopies.add(id);
    }
    public void addBorrowedBookID(Long id){
        borrowedCopies.add(id);
    }
    public int getNoOfAvailable() {
        return noOfAvailable;
    }
    public void setNoOfAvailable(int noOfAvailable) {
        this.noOfAvailable = noOfAvailable;
    }
    public int getNoOfBorrowed() {
        return noOfBorrowed;
    }
    public void setNoOfBorrowed(int noOfBorrowed) {
        this.noOfBorrowed = noOfBorrowed;
    }
    public List<Long> getAvailableCopies() {
        return availableCopies;
    }
    public List<Long> getBorrowedCopies() {
        return borrowedCopies;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookMetaData)) return false;
        BookMetaData that = (BookMetaData) o;
        return getNoOfAvailable() == that.getNoOfAvailable() && getNoOfBorrowed() == that.getNoOfBorrowed() && getTitle().equals(that.getTitle()) && getIsbn().equals(that.getIsbn()) && getAvailableCopies().equals(that.getAvailableCopies()) && getBorrowedCopies().equals(that.getBorrowedCopies()) && getAuthors().equals(that.getAuthors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getIsbn(), getNoOfAvailable(), getNoOfBorrowed(), getAvailableCopies(), getBorrowedCopies(), getAuthors());
    }

    @Override
    public String toString() {
        return "BookMetaData{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", noOfAvailable=" + noOfAvailable +
                ", noOfBorrowed=" + noOfBorrowed +
                ", availableCopies=" + availableCopies +
                ", borrowedCopies=" + borrowedCopies +
                ", authors=" + authors +
                "}\n";
    }
}
