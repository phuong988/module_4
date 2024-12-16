package com.example.list_customer_management.repository;

import com.example.list_customer_management.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
    public class CustomerRepository {
    private static final List<Customer> customers = new ArrayList<>();
    static {
        customers.add(new Customer(1, "Lê Viết Minh Phương", "phuong@gamil.com", "Đà Nẵng"));
        customers.add(new Customer(2, "Lê Thanh Tâm", "thanh@gmail.com", "Hà Nội"));
        customers.add(new Customer(3, "Lê Thanh Thanh", "thanh@gmail.com", "Đà Nẵng"));
        customers.add(new Customer(4, "Lê Thanh Thủy", "thanh@gmail.com", "Hồ Chí Minh"));
        customers.add(new Customer(5, "Lê Thanh Văn", "thanh@gmail.com", "Hà Nội"));
    }

    public static List<Customer> findAll() {
        return customers;
    }

}
