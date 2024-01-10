package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.CartItem;
import com.revature.revshopserver.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping(path = "/buyer/{buyer_id}/cart")
    public Set<CartItem> getCart(@PathVariable(name = "buyer_id") Integer buyerId) {
        return cartItemService.getCart(buyerId);
    }

    @DeleteMapping(path = "/buyer/{buyer_id}/cart")
    public void clearCart(@PathVariable(name = "buyer_id") Integer buyerId) {
        cartItemService.clearCart(buyerId);
    }

}
