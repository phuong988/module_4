package com.example.list_customer_management.service.impl;

import com.example.list_customer_management.model.Customer;
import com.example.list_customer_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer>  findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Object s) {

    }

    @Override
    public void update(int id, Object s) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findByName(String name) {
        return null;
    }

}
