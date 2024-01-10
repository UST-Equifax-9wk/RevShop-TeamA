package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Seller;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class SellerService {
    private final SellerRepository sellerRepository;


    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSeller(int id) throws ObjectNotFoundException {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isEmpty())
            throw new ObjectNotFoundException("Cannot find seller with that Id");
        return seller.get();
    }
    public Seller findSellerByAccountId(int id)throws ObjectNotFoundException {
        Optional<Seller> seller = sellerRepository.findByAccount_AccountId(id);
        if (seller.isEmpty())
            throw new ObjectNotFoundException("Cannot find seller with that Id");
        return seller.get();
    }
}
