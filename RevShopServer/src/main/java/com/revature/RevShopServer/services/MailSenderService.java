package com.revature.RevShopServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String recipient, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("revpay.a@gmail.com");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        //System.out.println("Message sent!");
    }
}
