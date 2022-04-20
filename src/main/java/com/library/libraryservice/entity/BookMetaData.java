package com.library.libraryservice.entity;

import java.util.List;

public class BookMetaData {
    private String title;
    private String isbn;
    private int noOfAvailable;
    private int noOfCopies;

    private List<Long> availableCopies;
    private List<Long> borrowedCopies;
    private List<Author> authors;

    public BookMetaData(String title, String isbn, int noOfAvailable, int noOfCopies, List<Long> availableCopies, List<Long> borrowedCopies, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.noOfAvailable = noOfAvailable;
        this.noOfCopies = noOfCopies;
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

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookDescription{" +
                "title='" + title + '\'' +
                ", \nisbn='" + isbn + '\'' +
                ", \nnoOfAvailable=" + noOfAvailable +
                ", \nnoOfCopies=" + noOfCopies +
                ", \nauthors=" + authors +
                '}';
    }
}
