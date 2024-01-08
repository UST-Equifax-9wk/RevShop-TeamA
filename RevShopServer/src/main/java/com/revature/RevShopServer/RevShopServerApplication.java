package com.revature.RevShopServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.revature.RevShopServer.entities",
		"com.revature.RevShopServer.controllers",
		"com.revature.RevShopServer.config",
		"com.revature.RevShopServer.services",
		"com.revature.RevShopServer.repositories"},
		exclude = { SecurityAutoConfiguration.class })
public class RevShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevShopServerApplication.class, args);
	}

}
