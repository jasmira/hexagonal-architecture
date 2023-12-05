package com.hexagonal.practice.hexagonal_architecture.application.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateOrderResponse {
    private final UUID id;

    public CreateOrderResponse(final UUID id) {
        this.id = id;
    }
}
