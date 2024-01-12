package com.revature.revshopserver.controllers;

import com.revature.revshopserver.dtos.BuyerDto;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerController {
    private final BuyerService buyerService;
    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/getBuyer")//give the Account id not the buyer id
    public BuyerDto getBuyer(@RequestParam Integer id) throws ObjectNotFoundException {
        return buyerService.getBuyerByAccountId(id);
    }
}
