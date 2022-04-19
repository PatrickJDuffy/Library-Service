package com.library.libraryservice.repository;


import com.library.libraryservice.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findByAuthorID(Long authorID);

}
