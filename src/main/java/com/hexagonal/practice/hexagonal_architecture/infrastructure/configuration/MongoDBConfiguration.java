package com.hexagonal.practice.hexagonal_architecture.infrastructure.configuration;

import com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.mongo.SpringDataMongoOrderRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoOrderRepository.class)
public class MongoDBConfiguration {
}
