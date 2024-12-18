package com.example.customer_data_management.controller;

import com.example.customer_data_management.model.Customer;
import com.example.customer_data_management.service.ICustomerService;
import com.example.customer_data_management.service.impl.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")
    public String index(Model model) {
        List<Customer> customersList = customerService.getAll();
        model.addAttribute("customers", customersList);
        return "index";
    }
}