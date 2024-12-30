package com.example.complete_shopping_cart.repository;

import com.example.complete_shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer > {
}
