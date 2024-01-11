package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // This query sums the quantities of all cart items of the same buyer and product
    // into the smallest cart_item_id, and then deletes the rest of the CartItems
    static final String AggregateQuery = "UPDATE cart_items as c1 " +
    "SET quantity = c2.total " +
    "FROM " +
        "(SELECT MIN(cart_item_id) as cid, " +
        "buyer_id, " +
        "product_id, " +
        "SUM(quantity) as total " +
        "FROM cart_items " +
        "GROUP BY buyer_id, product_id) as c2 " +
    "WHERE c1.cart_item_id = c2.cid AND " +
    "c1.buyer_id = c2.buyer_id AND " +
    "c1.product_id = c2.product_id; " +
    "DELETE FROM cart_items " +
    "WHERE cart_item_id NOT IN " +
        "(SELECT MIN(cart_item_id) " +
        "FROM cart_items " +
        "GROUP BY buyer_id, product_id)";

    @Query(value = "SELECT * FROM cart_items WHERE buyer_id = :buyer_id", nativeQuery = true)
    Set<CartItem> findAllByBuyerId(@Param("buyer_id") Integer buyerId);

    @Modifying
    @Query(value = "DELETE * FROM cart_items WHERE buyer_id = :buyer_id", nativeQuery = true)
    void deleteAllByBuyerId(@Param("buyer_id") Integer buyerId);

    @Modifying
    @Query(value = AggregateQuery, nativeQuery = true)
    void aggregate();
}
