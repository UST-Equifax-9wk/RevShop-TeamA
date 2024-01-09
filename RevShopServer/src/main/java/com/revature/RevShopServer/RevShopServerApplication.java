package com.revature.RevShopServer;

import com.revature.RevShopServer.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = {"com.revature.RevShopServer.entities",
		"com.revature.RevShopServer.controllers",
		"com.revature.RevShopServer.config",
		"com.revature.RevShopServer.services",
		"com.revature.RevShopServer.repositories",
		"com.revature.RevShopServer.dtos"},
		exclude = { SecurityAutoConfiguration.class })


public class RevShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevShopServerApplication.class, args);
	}


}
