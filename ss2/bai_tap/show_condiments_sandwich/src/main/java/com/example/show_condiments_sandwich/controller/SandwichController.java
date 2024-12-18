package com.example.show_condiments_sandwich.controller;

import com.example.show_condiments_sandwich.service.impl.SandwichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {
    private final SandwichService sandwichService;

    @Autowired
    public SandwichController(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }

    @GetMapping("/")
    public String showForm() {
        return "home";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value ="condiments", required = false) String[] condiments, Model model) {
        String[] condimentArray = sandwichService.getCheckCondiments(condiments);
        if (condimentArray.length > 0) {
            model.addAttribute("condiments", condimentArray);
        }
        return "result";
    }

}
