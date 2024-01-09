package com.revature.RevShopServer.services;

import com.revature.RevShopServer.entities.Account;
import com.revature.RevShopServer.entities.Seller;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.repositories.AccountRepository;
import com.revature.RevShopServer.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class SellerService {
    private SellerRepository sellerRepository;
    private AccountRepository accountRepository;

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
}