package com.example.list_customer_management.service.impl;

import java.util.List;

public interface ICustomerService<T>{
    public List<T> findAll();
    void save(T s);

    void update(int id, T s);

    void remove(int id);

    T findById(int id);

    List<T> findByName(String name);}
