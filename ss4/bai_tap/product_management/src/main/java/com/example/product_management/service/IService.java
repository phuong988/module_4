package com.example.product_management.service;

import com.example.product_management.model.Product;

import java.util.List;

public interface IService {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    boolean update(int id, Product product);
    boolean delete(int id);
    List<Product> searchByName(String name);
}
