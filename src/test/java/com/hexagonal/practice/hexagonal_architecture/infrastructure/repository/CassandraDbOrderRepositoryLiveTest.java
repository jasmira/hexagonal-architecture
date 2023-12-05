package com.hexagonal.practice.hexagonal_architecture.infrastructure.repository;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Order;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.Product;
import com.hexagonal.practice.hexagonal_architecture.domain.repository.OrderRepository;
import com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.cassandra.SpringDataCassandraOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:ddd-layers-test.properties")
public class CassandraDbOrderRepositoryLiveTest {
    @Autowired
    private SpringDataCassandraOrderRepository cassandraOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void cleanUp() {
        cassandraOrderRepository.deleteAll();
    }

    @Test
    void shouldFindById_thenReturnOrder() {

        // given
        final UUID id = UUID.randomUUID();
        final Order order = createOrder(id);
        order.addOrder(new Product(UUID.randomUUID(), BigDecimal.TEN, "second"));
        order.complete();

        // when
        orderRepository.save(order);

        final Optional<Order> result = orderRepository.findById(id);

        assertEquals(order, result.get());
    }

    private Order createOrder(UUID id) {
        return new Order(id, new Product(UUID.randomUUID(), BigDecimal.TEN, "product"));
    }
}
