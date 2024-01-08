package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.entities.Account;
import com.revature.RevShopServer.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccount(@Valid @RequestBody Account account)
    {
        return accountService.save(account);
    }
}
