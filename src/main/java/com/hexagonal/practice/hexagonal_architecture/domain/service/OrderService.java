package com.hexagonal.practice.hexagonal_architecture.domain.service;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderService {
    UUID createOrder(Product product);

    void addProduct(UUID id, Product product);

    void completeOrder(UUID id);

    void deleteProduct(UUID id, UUID productId);
}
