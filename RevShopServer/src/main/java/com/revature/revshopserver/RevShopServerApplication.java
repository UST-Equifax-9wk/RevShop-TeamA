package com.revature.revshopserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.revature.revshopserver.entities",
		"com.revature.revshopserver.controllers",
		"com.revature.revshopserver.config",
		"com.revature.revshopserver.services",
		"com.revature.revshopserver.repositories"},
		exclude = { SecurityAutoConfiguration.class })
public class RevShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevShopServerApplication.class, args);
	}


}
