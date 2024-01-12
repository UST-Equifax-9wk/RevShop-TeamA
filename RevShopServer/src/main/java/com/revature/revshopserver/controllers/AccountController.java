package com.revature.revshopserver.controllers;

import com.revature.revshopserver.dtos.JwtAuthenticationResponse;
import com.revature.revshopserver.dtos.SignInRequest;
import com.revature.revshopserver.entities.Account;
import com.revature.revshopserver.entities.Card;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.AccountService;
import com.revature.revshopserver.services.interfaces.AuthenticationService;
import com.revature.revshopserver.utils.EncryptionUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AccountController {

    private  static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccount(@Valid @RequestBody Account account) {
        logger.info("received request to register user");
        return accountService.save(account);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<Account>> getAllUsers() {
        logger.info("Received request to show all users");
        List<Account> accounts = accountService.getAllUsers();
        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<Account>> getUserByUsername(@PathVariable String username) {
        logger.info("Received request to show user by username");
        Optional<Account> user = accountService.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        logger.info("Received request to login");
        return ResponseEntity.ok(authenticationService.signin(request));
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
