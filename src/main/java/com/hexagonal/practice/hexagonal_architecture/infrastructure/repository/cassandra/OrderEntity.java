package com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.cassandra;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.Order;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.OrderItem;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.OrderStatus;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @PrimaryKey
    private UUID id;
    private OrderStatus status;
    private List<OrderItemEntity> orderItemEntities;
    private BigDecimal price;

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.status = order.getStatus();
        this.orderItemEntities = order.getOrderItems()
                .stream()
                .map(OrderItemEntity::new)
                .collect(Collectors.toList());

    }

    public Order toOrder() {
        List<OrderItem> orderItems = orderItemEntities.stream()
                .map(OrderItemEntity::toOrderItem)
                .toList();
        List<Product> namelessProducts = orderItems.stream()
                .map(orderItem -> new Product(orderItem.getProductId(), orderItem.getPrice(), ""))
                .collect(Collectors.toList());
        Order order = new Order(id, namelessProducts.remove(0));
        namelessProducts.forEach(product -> order.addOrder(product));
        if (status == OrderStatus.COMPLETED) {
            order.complete();
        }
        return order;
    }
}
