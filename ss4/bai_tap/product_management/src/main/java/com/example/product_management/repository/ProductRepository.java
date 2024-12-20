package com.example.product_management.repository;


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
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public void save(Product product) {
        product.setId(products.size() + 1);
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
        List<Product> updatedProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getId() != id) {
                updatedProducts.add(product);
            }
        }
        products.clear();
        products.addAll(updatedProducts);
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
