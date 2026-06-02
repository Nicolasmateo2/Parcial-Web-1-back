package com.parcialback.parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "co.javeriana.dw.organizapp")
@EntityScan(basePackages = "co.javeriana.dw.organizapp.model")
@EnableJpaRepositories(basePackages = "co.javeriana.dw.organizapp.repository")
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}

}