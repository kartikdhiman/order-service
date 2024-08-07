package com.polarbookshop.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestOrderServiceApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>("postgres:16-alpine");
	}

	public static void main(String[] args) {
		SpringApplication.from(OrderServiceApplication::main).with(TestOrderServiceApplication.class).run(args);
	}

}
