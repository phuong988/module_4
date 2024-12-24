package com.example.product_management.repository;

import com.example.product_management.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        List<Product> products = BaseRepository.entityManager.createQuery("from products", Product.class).getResultList();
        return products;
    }

    public Product findById(int id) {
        Product product = BaseRepository.entityManager.find(Product.class, id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        return product;
    }

    public void save(Product product) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            BaseRepository.entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new IllegalArgumentException("Error saving product: " + e.getMessage());
        }
    }

    public boolean update(int id, Product product) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            Product existingProduct = findById(id);
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setManufacturer(product.getManufacturer());
            BaseRepository.entityManager.merge(existingProduct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }
    public boolean delete(int id) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            Product product = findById(id);
            BaseRepository.entityManager.remove(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public List<Product> searchByName(String name) {
        return BaseRepository.entityManager.createQuery(
                        "FROM products p WHERE LOWER(p.name) LIKE :name", Product.class)
                .setParameter("name", "%" + name.toLowerCase() + "%")
                .getResultList();
    }
}