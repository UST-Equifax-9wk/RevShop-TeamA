package com.revature.revshopserver.controllers;


import com.revature.revshopserver.entities.Discount;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.DiscountService;
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
}
