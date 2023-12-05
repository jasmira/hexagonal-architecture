package com.hexagonal.practice.hexagonal_architecture.infrastructure.repository.cassandra;

import com.hexagonal.practice.hexagonal_architecture.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.util.UUID;

@UserDefinedType
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    private UUID productId;
    private BigDecimal price;

    public OrderItemEntity(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.price = orderItem.getPrice();
    }

    public OrderItem toOrderItem() {
        return new OrderItem(new Product(productId, price, ""));
    }
}
