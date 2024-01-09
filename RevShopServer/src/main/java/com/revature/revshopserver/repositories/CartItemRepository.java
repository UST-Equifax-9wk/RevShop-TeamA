package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT * FROM cart_items WHERE buyer_id = :buyer_id", nativeQuery = true)
    Set<CartItem> findAllByBuyerId(@Param("buyer_id") Integer buyerId);
}
