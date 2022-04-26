package com.library.libraryservice.service;

import com.library.libraryservice.entity.Borrow;
import com.library.libraryservice.entity.Status;
import com.library.libraryservice.exception.BookAvailabilityException;
import com.library.libraryservice.exception.InstanceNotFoundException;
import com.library.libraryservice.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Borrow Service that works between the repositroy and controller that does most of the operations on the data
@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookService bookService;

    //Takes Borrow object persists and changed status of book to Borrowed
    public Borrow saveBorrow(Borrow borrow) throws BookAvailabilityException {
        if(bookService.findBookByID(borrow.getBookID()).getStatus() == Status.AVAILABLE){
            Borrow saved = borrowRepository.save(borrow);
            bookService.UpdateStatus(borrow.getBookID(), Status.BORROWED);
            return saved;
        }
        else {
            throw new BookAvailabilityException();
        }
    }

    public Borrow findBorrowByID(Long borrowID) throws InstanceNotFoundException{
        Borrow borrow;
        try{
            borrow =borrowRepository.findByBorrowID(borrowID);
        }catch(Exception e){
            throw new InstanceNotFoundException();
        }
        return borrow;
    }
}
