package com.library.libraryservice.repository;


import com.library.libraryservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository interface to query the JPA
@Repository
public interface BookRepository extends JpaRepository<Book, Long >{

    Book findByBookID(Long bookID);

    @Query("select b from Book b inner join b.authors authors where upper(authors.name) = upper(?1)")
    List<Book> findByAuthors_Name(String name);

    List<Book> findByTitleIgnoreCase(String title);

}
