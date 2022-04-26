package com.library.libraryservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Author")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long authorID;
    @Column(name="author_name")
    private String name;

    public Author(){

    }
    public Author(String name) {
        this.name = name;
    }


    //Getters + Setters
    public Long getAuthorID()
    {
        return authorID;
    }
    public void setAuthorID(Long authorID)
    {
        this.authorID = authorID;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
}
