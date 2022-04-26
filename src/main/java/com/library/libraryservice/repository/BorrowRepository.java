package com.library.libraryservice.repository;


import com.library.libraryservice.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository interface used to query the JPA
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findByBorrowID(Long borrowID);
}
