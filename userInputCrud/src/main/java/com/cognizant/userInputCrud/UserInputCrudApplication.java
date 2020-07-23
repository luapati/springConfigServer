package com.cognizant.userInputCrud;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
public class UserInputCrudApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(UserInputCrudApplication.class, args);
		
	}
}
