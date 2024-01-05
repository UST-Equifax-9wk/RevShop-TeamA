package com.revature.RevShopServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.revature.RevShopServer.entities"})
public class RevShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevShopServerApplication.class, args);
	}

}
