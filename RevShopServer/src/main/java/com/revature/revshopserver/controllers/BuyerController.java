package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping(path = "/all-buyers")
    public Set<Buyer> getAllBuyers() {
        return this.buyerService.getAllBuyers();
    }

    @GetMapping(path = "/buyer/{buyer_id}")
    public Buyer getBuyerById(@PathVariable("buyer_id") Integer buyerId) throws ObjectNotFoundException {
        return this.buyerService.getBuyerById(buyerId);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
