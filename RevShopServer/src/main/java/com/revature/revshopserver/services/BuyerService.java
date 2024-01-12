package com.revature.revshopserver.services;

import com.revature.revshopserver.dtos.BuyerDto;
import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.BuyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;



@Service
@Transactional(Transactional.TxType.REQUIRED)
public class BuyerService {

    private final BuyerRepository buyerRepository;
    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }
    //give the Account id not the buyer id
    public BuyerDto getBuyerByAccountId(Integer id) throws ObjectNotFoundException {
        Optional<Buyer> buyer = buyerRepository.getBuyerByAccount_AccountId(id);
        if(buyer.isEmpty())
        {
            throw new ObjectNotFoundException("Cannot find buyer with that Id");
        }
        return new BuyerDto(buyer.get());
    }



    // want to use a list instead of Set, so I can use default findAll
    public Set<Buyer> getAllBuyers() {
        return buyerRepository.findAllBuyers();
    }

    public Buyer getBuyerById(Integer buyerId) throws ObjectNotFoundException {
        Optional<Buyer> buyerLookup = buyerRepository.findById(buyerId);
        if (buyerLookup.isEmpty()) {
            throw new ObjectNotFoundException("Buyer not found in database!");
        }
        return buyerLookup.get();
    }

}
