package com.library.libraryservice.controller;

import com.library.libraryservice.entity.Borrow;
import com.library.libraryservice.exception.BookAvailabilityException;
import com.library.libraryservice.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Controller API to deal with requests and routes them to BorrowService for operations
@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    //Takes borrowID from url path and sends it to BookService to find borrow instance
    @GetMapping("/{id}")
    public Borrow findBorrowByID(@PathVariable("id") Long borrowID){
        return borrowService.findBorrowByID(borrowID);
    }

    //TODO - Current time for borrowDateTime(Possibly frontend as parameters are final in Borrow Object) cant do it serverside
    //Takes Borrow object from JSON in request body then saves it through the BorrowService
    @PostMapping("/")
    public Borrow saveBorrow(@RequestBody Borrow borrow){
        try {
            return borrowService.saveBorrow(borrow);
        } catch (BookAvailabilityException e) {
            throw new RuntimeException(e);
        }
    }

}
