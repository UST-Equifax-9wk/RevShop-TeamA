package com.revature.revshopserver.repositories;

import com.revature.revshopserver.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import java.util.Optional;
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Optional<Buyer> getBuyerByAccount_AccountId(Integer id);


    @Query(value = "SELECT * FROM buyers", nativeQuery = true)
    Set<Buyer> findAllBuyers();

    Optional<Buyer> findByAccount_AccountId(Integer accountId);
}
