package com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.mongo;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataMongoOrderRepository extends MongoRepository<Order, UUID> {
}
