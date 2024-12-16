package com.example.currency_conversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ConverterController {
    //    @Autowired
    @GetMapping("")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convertCurrency(@RequestParam("usd") double usd,
                                  Model model) {
        double rate =25.4;
        double vnd = usd * rate;
        model.addAttribute("vnd", vnd);
        return "result";
    }
}