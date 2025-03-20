package com.stiven4950.puntored.api_transaccional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class ApiTransaccionalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiTransaccionalApplication.class, args);
	}
}
