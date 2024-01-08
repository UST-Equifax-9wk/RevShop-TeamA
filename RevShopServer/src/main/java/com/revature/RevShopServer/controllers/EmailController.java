package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.entities.Email;
import com.revature.RevShopServer.services.MailSenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmailController {
    private MailSenderService mailSenderService;

    @Autowired
    public EmailController(MailSenderService mailSenderService){
        this.mailSenderService = mailSenderService;
    }
    @PostMapping(path = "/sendEmail")
    @ResponseStatus(HttpStatus.OK)
    public Email sendEmail(@Valid @RequestBody Email email){
        mailSenderService.sendEmail(email.getRecipient(), email.getSubject(), email.getMessage());
        return email;
    }

}
