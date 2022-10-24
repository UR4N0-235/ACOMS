package com.br.acoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AcomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcomsApplication.class, args);
	}
}
