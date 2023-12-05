package com.hexagonal.practice.hexagonal_architecture.domain.repository;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository {
    Optional<Order> findById(UUID id);

    void save(Order order);
}
