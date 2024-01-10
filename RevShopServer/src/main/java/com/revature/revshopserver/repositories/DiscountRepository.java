package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    @Query(value = "SELECT * FROM discounts WHERE product_id = :product_id", nativeQuery = true)
    Set<Discount> findAllByProductId(@Param("product_id") Integer productId);
}
