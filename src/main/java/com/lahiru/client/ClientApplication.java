package com.lahiru.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lahiru.client.resource.ProductResourceClient;

@SpringBootApplication
public class ClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
		
		ProductResourceClient.findAll();
		ProductResourceClient.save();
	}
	
}
