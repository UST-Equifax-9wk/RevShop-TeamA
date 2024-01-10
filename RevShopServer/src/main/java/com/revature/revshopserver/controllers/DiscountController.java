package com.revature.revshopserver.controllers;


import com.revature.revshopserver.entities.Discount;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/{productId}/newDiscount")
    public Discount postNewDiscount(@RequestBody Discount discount, @PathVariable int productId) throws ObjectNotFoundException {
        return discountService.saveDiscount(discount, productId);
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
