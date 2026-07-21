package com.codewithlei.e_commerce.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ECommerceWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebsiteApplication.class, args);

        //test google client
        System.out.println("Client ID loaded: " + System.getenv("GOOGLE_CLIENT_ID"));
	}

}
