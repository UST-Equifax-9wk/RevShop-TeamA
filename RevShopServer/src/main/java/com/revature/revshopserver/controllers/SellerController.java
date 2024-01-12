package com.revature.revshopserver.controllers;

import com.revature.revshopserver.dtos.SellerDto;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {
    private final SellerService sellerService;
    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }
    //returns the seller's id and address
    @GetMapping(path = "/getselleraddress")
    public SellerDto getAddress(@RequestParam Integer id) throws ObjectNotFoundException {
        return sellerService.getSellerAddress(id);
    }
}
