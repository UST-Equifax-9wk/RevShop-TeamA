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
import java.util.NoSuchElementException;
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
    public Account registerAccount(@Valid @RequestBody Account account,@RequestParam(required = false) String address
    ,@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname) {
        logger.info("received request to register user");
        return accountService.save(account,address,firstname,lastname);
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


    // Handling this error for JWTToken not finding a username on lookup for generateToken
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
