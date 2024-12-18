package com.example.customer_data_management.service;

import com.example.customer_data_management.model.Customer;

import java.util.List;

public interface IService {
    List<Customer> getAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id,Customer customer);

    void remove(int id);

}
