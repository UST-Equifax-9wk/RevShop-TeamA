package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.repositories.BuyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class BuyerService {

    private final BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    // want to use a list instead of Set, so I can use default findAll
    public Set<Buyer> getAllBuyers() {
        return this.buyerRepository.findAllBuyers();
    }

}
