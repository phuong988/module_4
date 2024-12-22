package com.example.product_management.repository;


import com.example.product_management.exception.ProductNotFoundException;
import com.example.product_management.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "iPhone 14", 999.99, "Latest iPhone model", "Apple"));
        products.add(new Product(2, "Samsung Galaxy S23", 899.99, "Flagship Samsung phone", "Samsung"));
        products.add(new Product(3, "MacBook Pro", 1999.99, "High-performance laptop", "Apple"));
    }

    public List<Product> findAll() {
        List<Product> products = BaseRepository.entityManager.createQuery("from products", Product.class).getResultList();
        return products;
    }

    public Product findById(int id) {
        Product product = BaseRepository.entityManager.find(Product.class, id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }


    public void save(Product product) {
        products.add(product);
        EntityTransaction transaction  = BaseRepository.entityManager.getTransaction();
        transaction.begin();
        BaseRepository.entityManager.persist(product);
        transaction.commit();
    }


    public void update(int id, Product product) {
        Product existingProduct = findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setManufacturer(product.getManufacturer());
        }
    }

    public void delete(int id) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        transaction.begin();
        Product product = findById(id);
        BaseRepository.entityManager.remove(product);
        transaction.commit();
    }

    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }

        return result;
    }
}
