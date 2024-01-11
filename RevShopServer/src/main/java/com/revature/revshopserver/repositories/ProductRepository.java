package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products WHERE seller_id = :seller_id", nativeQuery = true)
    Set<Product> findAllBySellerId(@Param("seller_id") Integer sellerId);
}
