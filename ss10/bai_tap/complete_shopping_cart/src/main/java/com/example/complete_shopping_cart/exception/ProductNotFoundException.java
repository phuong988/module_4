package com.example.complete_shopping_cart.exception;

public class ProductNotFoundException extends RuntimeException  {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
