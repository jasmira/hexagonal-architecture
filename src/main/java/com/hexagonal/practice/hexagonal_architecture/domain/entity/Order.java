package com.hexagonal.practice.hexagonal_architecture.domain.entity;

import com.hexagonal.practice.hexagonal_architecture.domain.exception.DomainException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Collections;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private UUID id;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal price;

    public Order(final UUID id, final Product product) {
        this.id = id;
        this.orderItems = new ArrayList<>(Collections.singletonList(new OrderItem(product)));
        this.status = OrderStatus.CREATED;
        this.price = product.getPrice();
    }

    public void complete() {
        validateState();
        this.status = OrderStatus.COMPLETED;
    }

    public void addOrder(final Product product) {
        validateState();
        validateProduct(product);
        orderItems.add(new OrderItem(product));
        price = price.add(product.getPrice());
    }

    public void removeOrder(final UUID id) {
        validateState();
        final OrderItem orderItem = getOrderItem(id);
        orderItems.remove(orderItem);
        price = price.subtract(orderItem.getPrice());
    }

    private OrderItem getOrderItem(final UUID id) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.getProductId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    private void validateState() {
        if (OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItems, price, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Order))
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id) && Objects.equals(orderItems, other.orderItems) && Objects.equals(price, other.price) && status == other.status;
    }

    private Order() {
    }
}
