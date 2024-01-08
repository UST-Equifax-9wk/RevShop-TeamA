package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.entities.Account;
import com.revature.RevShopServer.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/register")
    public Account registerAccount(@Valid @RequestBody Account account)
    {
        return accountService.save(account);
    }
}
