package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

}
