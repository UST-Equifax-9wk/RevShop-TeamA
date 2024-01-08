package com.revature.RevShopServer;

import com.revature.RevShopServer.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = {"com.revature.RevShopServer.entities", "com.revature.RevShopServer.services"} )
public class RevShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevShopServerApplication.class, args);
	}


}
