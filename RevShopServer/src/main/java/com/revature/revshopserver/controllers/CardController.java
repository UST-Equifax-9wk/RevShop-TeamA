package com.revature.revshopserver.controllers;

import com.revature.revshopserver.entities.Account;
import com.revature.revshopserver.entities.Card;
import com.revature.revshopserver.services.AccountService;
import com.revature.revshopserver.utils.EncryptionUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public class CardController {

    private  static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final AccountService accountService;

    public CardController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/cards/add")
    public ResponseEntity<Card> addCard(@Valid @RequestBody Card card, @RequestParam Integer accountId) throws Exception {
        logger.info("Received request to add card");
        Account account = accountService.getAccountByAccountId(accountId);

        Card addedCard = accountService.addCardToAccount(account, card);
        logger.info("Card added!");
        return ResponseEntity.ok(addedCard);
    }

    @GetMapping(path = "/cards/all")
    public ResponseEntity<Set<Card>> findCardByAccountId(@RequestParam String accountId) throws Exception {
        logger.info("Received request to show all cards");
        Account account = accountService.getAccountByAccountId(Integer.parseInt(accountId));
        for (Card card: account.getCards()) {
            card.setCardNumber( EncryptionUtil.decrypt(card.getCardNumber()));
        }
        logger.info("showing all cards by account");
        return ResponseEntity.ok(account.getCards());
    }
}
