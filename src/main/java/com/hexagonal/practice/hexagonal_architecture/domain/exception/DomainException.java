package com.hexagonal.practice.hexagonal_architecture.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
