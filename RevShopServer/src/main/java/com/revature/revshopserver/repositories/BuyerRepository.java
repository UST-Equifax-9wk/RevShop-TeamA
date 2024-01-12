package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Optional<Buyer> getBuyerByAccount_AccountId(Integer id);
}
