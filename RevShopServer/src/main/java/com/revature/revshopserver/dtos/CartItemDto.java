package com.revature.revshopserver.dtos;

import com.revature.revshopserver.entities.CartItem;

public class CartItemDto {

    private Integer cartItemId;
    private Integer productId;
    private Integer quantity;

    public CartItemDto() {
    }

    public CartItemDto(Integer productId, Integer quantity) {
        this.cartItemId = null;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItemDto(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.productId = cartItem.getProduct().getProductId();
        this.quantity = cartItem.getQuantity();
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "cartItemId=" + cartItemId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
