package com.example.complete_shopping_cart.service;

import com.example.complete_shopping_cart.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    Product saveProduct(Product product);
    void deleteProduct(int id);

}
