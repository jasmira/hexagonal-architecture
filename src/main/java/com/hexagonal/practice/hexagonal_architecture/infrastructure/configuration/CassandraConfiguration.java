package com.hexagonal.practice.hexagonal_architecture.infrastructure.configuration;

import com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.cassandra.SpringDataCassandraOrderRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackageClasses = SpringDataCassandraOrderRepository.class)
public class CassandraConfiguration {
}
