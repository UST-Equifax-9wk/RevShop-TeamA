package com.revature.revshopserver.services;

import com.revature.revshopserver.dtos.CartItemDto;
import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.entities.CartItem;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final BuyerService buyerService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository,
                           ProductService productService,
                           BuyerService buyerService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.buyerService = buyerService;
    }

    public Set<CartItem> getCart(Integer buyerId) {
        return cartItemRepository.findAllByBuyerId(buyerId);
    }

    public CartItem addToCart(Integer buyerId, CartItemDto cartItemDto) throws ObjectNotFoundException {
        Product product = productService.getById(cartItemDto.getProductId());
        Buyer buyer = buyerService.getBuyerById(buyerId);
        CartItem out =  cartItemRepository.save(new CartItem(
                buyer,
                product,
                cartItemDto.getQuantity()
        ));
        cartItemRepository.aggregate();
        return out;
    }
    public void clearCart(Integer buyerId) {
        cartItemRepository.deleteAllByBuyerId(buyerId);
    }

    public void aggregateCartItems() {
        cartItemRepository.aggregate();
    }
}
