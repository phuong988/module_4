package com.example.complete_shopping_cart.service;

import com.example.complete_shopping_cart.model.Product;

import java.util.Map;

public interface ICartService {
    void addToCart(Product product);
    Map<Product, Integer> getCartItems();
    boolean removeFromCart(Product product);
    boolean updateQuantity(Product product, int quantity);
    void clearCart();
    double getTotal();
    boolean containsProduct(Product product);
    int getQuantity(Product product);
}
