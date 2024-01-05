package com.revature.RevShopServer.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "favorites")
public class Favorite {

    @EmbeddedId
    FavoriteKey id;

    @ManyToOne
    @MapsId("buyerId")
    @JoinColumn(name = "buyer_id")
    Integer buyerId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Integer productId;

    public Favorite() {
    }

    public Favorite(FavoriteKey id, Integer buyerId, Integer productId) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public FavoriteKey getId() {
        return id;
    }

    public void setId(FavoriteKey id) {
        this.id = id;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id) && Objects.equals(buyerId, favorite.buyerId) && Objects.equals(productId, favorite.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerId, productId);
    }
}
