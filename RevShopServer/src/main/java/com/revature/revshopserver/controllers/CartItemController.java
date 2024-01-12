package com.revature.revshopserver.controllers;

import com.revature.revshopserver.dtos.CartItemDto;
import com.revature.revshopserver.entities.CartItem;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.CartItemRepository;
import com.revature.revshopserver.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping(path = "/buyer/{buyer_id}/cart")
    public Set<CartItemDto> getCart(@PathVariable("buyer_id") Integer buyerId) {
        return cartItemService.getCart(buyerId)
                .stream()
                .map(CartItemDto::new)
                .collect(Collectors.toSet());
    }

    // TODO: alter behavior depending on implementation of products in frontend
    // want to return a DTO so it will display the product ID without being hidden by JsonBackReference
    @PostMapping(path = "/buyer/{buyer_id}/cart")
    public CartItemDto addToCart(@PathVariable("buyer_id") Integer buyerId,
                             // subject to change depending on if Product DTO exists
                             @RequestBody CartItemDto cartItemDto) throws ObjectNotFoundException {
        return new CartItemDto(cartItemService.addToCart(buyerId, cartItemDto));
    }

    @DeleteMapping(path = "/buyer/{buyer_id}/cart")
    public void clearCart(@PathVariable("buyer_id") Integer buyerId) {
        cartItemService.clearCart(buyerId);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
