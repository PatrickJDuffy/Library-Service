package com.library.libraryservice.controller;

import com.library.libraryservice.entity.Borrow;
import com.library.libraryservice.exception.BookAvailabilityException;
import com.library.libraryservice.exception.InstanceNotFoundException;
import com.library.libraryservice.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Controller API to deal with requests and routes them to BorrowService for operations
@RestController
@RequestMapping("/borrows")
@Slf4j
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    //Takes borrowID from url path and sends it to BookService to find borrow instance
    @GetMapping("/{id}")
    public Borrow findBorrowByID(@PathVariable("id") Long borrowID) throws InstanceNotFoundException{
        try{
            return borrowService.findBorrowByID(borrowID);
        }catch(InstanceNotFoundException e){
            log.info(e.getMessage());
            throw new InstanceNotFoundException();
        }
    }

    //TODO - Current time for borrowDateTime(Possibly frontend as parameters are final in Borrow Object) cant do it serverside
    //Takes Borrow object from JSON in request body then saves it through the BorrowService
    @PostMapping("/")
    public Borrow saveBorrow(@RequestBody Borrow borrow) throws InstanceNotFoundException{
        try {
            return borrowService.saveBorrow(borrow);
        } catch (BookAvailabilityException e) {
            log.info(e.getMessage());
            throw new InstanceNotFoundException();
        }
    }

}
