package com.example.show_condiments_sandwich.service.impl;

import com.example.show_condiments_sandwich.service.ISandwichService;
import org.springframework.stereotype.Service;

@Service
public class SandwichService implements ISandwichService {
    public String[] getCheckCondiments(String[] condiments) {
        return (condiments != null) ? condiments : new String[] {};
    }
}
