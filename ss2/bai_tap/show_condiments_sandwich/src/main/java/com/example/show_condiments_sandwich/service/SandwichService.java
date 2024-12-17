package com.example.show_condiments_sandwich.service;

import org.springframework.stereotype.Service;

@Service
public class SandwichService {
    public String[] getCheckCondiments(String[] condiments) {
        return (condiments != null) ? condiments : new String[] {};
    }
}
