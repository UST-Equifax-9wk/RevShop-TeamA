package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "sellers_id")
    private Sellers sellers;

    @Column(nullable = false)
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Long cartItemId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", quantity=" + quantity +
                '}';
    }
}
