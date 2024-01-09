package com.revature.RevShopServer.repositories;

import com.revature.RevShopServer.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews WHERE product_id = :product_id", nativeQuery = true)
    Set<Review> findAllByProductId(@Param("product_id") Integer productId);

    @Query(value = "SELECT * FROM reviews WHERE buyer_id = :buyer_id", nativeQuery = true)
    Set<Review> findAllByBuyerId(@Param("buyer_id") Integer buyerId);
}
