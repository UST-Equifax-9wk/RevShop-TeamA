package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    public Optional<Seller> findByAccount_AccountId(int id);

}
