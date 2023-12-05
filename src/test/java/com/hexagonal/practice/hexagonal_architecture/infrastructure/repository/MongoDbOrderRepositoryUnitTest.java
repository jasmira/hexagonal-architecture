package com.hexagonal.practice.hexagonal_architecture.infrastructure.repository;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Order;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.Product;
import com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.mongo.MongoDbOrderRepository;
import com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.mongo.SpringDataMongoOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MongoDbOrderRepositoryUnitTest {
    private SpringDataMongoOrderRepository springDataOrderRepository;
    private MongoDbOrderRepository tested;

    @BeforeEach
    void setUp() {
        springDataOrderRepository = mock(SpringDataMongoOrderRepository.class);

        tested = new MongoDbOrderRepository(springDataOrderRepository);
    }

    @Test
    void shouldFindById_thenReturnOrder() {
        final UUID id = UUID.randomUUID();
        final Order order = createOrder(id);
        when(springDataOrderRepository.findById(id)).thenReturn(Optional.of(order));

        final Optional<Order> result = tested.findById(id);

        assertEquals(order, result.get());
    }

    @Test
    void shouldSaveOrder_viaSpringDataOrderRepository() {
        final UUID id = UUID.randomUUID();
        final Order order = createOrder(id);

        tested.save(order);

        verify(springDataOrderRepository).save(order);
    }

    private Order createOrder(UUID id) {
        return new Order(id, new Product(UUID.randomUUID(), BigDecimal.TEN, "product"));
    }
}
