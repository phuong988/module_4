package com.example.list_customer_management.controller;

import com.example.list_customer_management.model.Customer;
import com.example.list_customer_management.repository.CustomerRepository;
import com.example.list_customer_management.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView getAllCustomers() {
        return new ModelAndView("customer/list", "customers", customerService.findAll());
    }
}
