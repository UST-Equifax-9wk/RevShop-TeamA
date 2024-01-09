package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.CartItem;
import com.revature.revshopserver.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public Set<CartItem> getCart(Integer buyerId) {
        return cartItemRepository.findAllByBuyerId(buyerId);
    }

}
