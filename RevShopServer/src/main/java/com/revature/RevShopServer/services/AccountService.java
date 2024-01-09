package com.revature.RevShopServer.services;

import com.revature.RevShopServer.entities.Account;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Seller;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.repositories.AccountRepository;
import com.revature.RevShopServer.repositories.BuyerRepository;
import com.revature.RevShopServer.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }
    public Account save(Account account)
    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account result = accountRepository.save(account);
        if(result.getAccountType().equals("buyer"))
        {
            Buyer buyer = new Buyer(result);
            buyerRepository.save(buyer);
        }
        else if(result.getAccountType().equals("seller"))
        {
            Seller seller = new Seller(result);
            sellerRepository.save(seller);
        }
        return result;
    }

    public Account getAccountByAccountId(int id) throws ObjectNotFoundException {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty())
            throw new ObjectNotFoundException("Cannot find account with that Id");
        return account.get();
    }
}
