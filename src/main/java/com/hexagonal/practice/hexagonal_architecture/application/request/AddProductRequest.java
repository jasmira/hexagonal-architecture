package com.hexagonal.practice.hexagonal_architecture.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hexagonal.practice.hexagonal_architecture.domain.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddProductRequest {
    @NotNull
    private Product product;

    @JsonCreator
    public AddProductRequest(@JsonProperty("product") final Product product) {
        this.product = product;
    }
}
