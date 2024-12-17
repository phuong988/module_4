package com.example.currency_conversion.controller;

import com.example.currency_conversion.service.ConverterService;
import com.example.currency_conversion.service.IConverterService;
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
    private final IConverterService converterService;
    @Autowired
    public ConverterController(IConverterService converterService) {
        this.converterService = converterService;
    }
    @GetMapping("")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convertCurrency(@RequestParam("usd") double usd,
                                  Model model) {
      if(!converterService.checkCurrency(usd)) {
          model.addAttribute("error", "Invalid amount. Please enter a positive number.");
          return "index";
      }
      double vnd = converterService.convertCurrency(usd);
      model.addAttribute("usd", usd);
      model.addAttribute("vnd", vnd);
      model.addAttribute("rate", converterService.RATE);
      return "result";
    }
}