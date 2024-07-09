package com.polarbookshop.orderservice.order.domain;

import com.polarbookshop.orderservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@Import(DataConfig.class)
@Testcontainers
class OrderRepositoryTests {
	@Container
	static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:16");

	@Autowired
	private OrderRepository orderRepository;


	@Test
	void connectionEstablished() {
		assertThat(postgresql.isRunning()).isTrue();
	}

	@Test
	void findOrderByIdWhenNotExisting() {
		StepVerifier.create(orderRepository.findById(394L))
						.expectNextCount(0)
						.verifyComplete();
	}

	@Test
	void createRejectedOrder() {
		var rejectedOrder = OrderService.buildRejectedOrder("1234567890", 3);
		StepVerifier.create(orderRepository.save(rejectedOrder))
						.expectNextMatches(order -> order.status().equals(OrderStatus.REJECTED))
						.verifyComplete();
	}
}
