package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server url")
		}
)
public class SnackApplication {
	public static void main(String[] args) {
		SpringApplication.run(SnackApplication.class, args);
	}
}
