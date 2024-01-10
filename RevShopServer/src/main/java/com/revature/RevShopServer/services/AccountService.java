package com.revature.RevShopServer.services;

import com.revature.RevShopServer.entities.Account;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Seller;
import com.revature.RevShopServer.repositories.AccountRepository;
import com.revature.RevShopServer.repositories.BuyerRepository;
import com.revature.RevShopServer.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService{
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

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

}
