package com.hexagonal.practice.hexagonal_architecture.infrastructure.configuration;

import com.hexagonal.practice.hexagonal_architecture.HexagonalArchitectureApplication;
import com.hexagonal.practice.hexagonal_architecture.domain.repository.OrderRepository;
import com.hexagonal.practice.hexagonal_architecture.domain.service.DomainOrderService;
import com.hexagonal.practice.hexagonal_architecture.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = HexagonalArchitectureApplication.class)
public class BeanConfiguration {

    @Bean
    OrderService orderService(final OrderRepository orderRepository) {
        return new DomainOrderService(orderRepository);
    }
}
