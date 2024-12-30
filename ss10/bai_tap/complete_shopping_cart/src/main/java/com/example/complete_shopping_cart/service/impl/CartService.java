package com.example.complete_shopping_cart.service.impl;

import com.example.complete_shopping_cart.model.Product;
import com.example.complete_shopping_cart.service.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService implements ICartService {
    private Map<Product, Integer> items = new HashMap<>();

    @Override
    public void addToCart(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    @Override
    public boolean removeFromCart(Product product) {
        if (items.containsKey(product)) {
            items.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateQuantity(Product product, int quantity) {
        if (items.containsKey(product)) {
            if (quantity <= 0) {
                items.remove(product);
            } else {
                items.put(product, quantity);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clearCart() {
        items.clear();
    }
    @Override
    public Map<Product, Integer> getCartItems() {
        return new HashMap<>(items);
    }

    @Override
    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getDiscountedPrice() * entry.getValue())
                .sum();
    }

    @Override
    public boolean containsProduct(Product product) {
        return items.containsKey(product);
    }

    @Override
    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }

}
