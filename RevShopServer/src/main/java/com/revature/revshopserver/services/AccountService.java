package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Account;
import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.entities.Seller;
import com.revature.revshopserver.repositories.AccountRepository;
import com.revature.revshopserver.repositories.BuyerRepository;
import com.revature.revshopserver.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
