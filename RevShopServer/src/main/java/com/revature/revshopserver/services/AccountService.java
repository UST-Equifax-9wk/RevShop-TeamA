package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Account;
import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.entities.Card;
import com.revature.revshopserver.entities.Seller;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.AccountRepository;
import com.revature.revshopserver.repositories.BuyerRepository;
import com.revature.revshopserver.repositories.CardRepository;
import com.revature.revshopserver.repositories.SellerRepository;
import com.revature.revshopserver.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private CardRepository cardRepository;

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
        if("BUYER".equalsIgnoreCase(result.getAccountType().name()))
        {
            Buyer buyer = new Buyer(result);
            buyerRepository.save(buyer);
        }
        else if("SELLER".equalsIgnoreCase(result.getAccountType().name()))
        {
            Seller seller = new Seller(result);
            sellerRepository.save(seller);
        }
        return result;
    }

    public Account getAccountByAccountId(int id) throws ObjectNotFoundException {
        Optional<Account> account = accountRepository.findById(id);
        account.ifPresent(value -> logger.info("AccountService - getAccountByAccountId - found account: " + value));
        if (account.isEmpty())
            throw new ObjectNotFoundException("Cannot find account with that Id");
        return account.get();
    }

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

//    public Optional<Account> findByAccountId(Integer accountId) {
//        return accountRepository.findAccountById(accountId);
//    }

    @Transactional
    public Card addCardToAccount(Account account, Card cardDetails) throws Exception {
        cardDetails.setCardNumber(encryptCard(cardDetails.getCardNumber()));
        cardDetails.setAccount(account);
        Card savedCard = null;
        try {
            savedCard = cardRepository.save(cardDetails);
        } catch (Exception e) {
            logger.error("failed to add card to account: " + Arrays.toString(e.getStackTrace()));
        }
        return savedCard;
    }

    private String encryptCard(String cardNumber) throws Exception {
        return EncryptionUtil.encrypt(cardNumber);
    }

}
