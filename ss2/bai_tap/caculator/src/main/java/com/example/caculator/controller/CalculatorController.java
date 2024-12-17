package com.example.caculator.controller;

import com.example.caculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/calculate")
    public String calculate(@RequestParam double num1,
                            @RequestParam double num2,
                            @RequestParam String operation,
                            Model model) {
        try {
            double result = calculatorService.calculate(num1, num2, operation);
            model.addAttribute("result", result);
        } catch (ArithmeticException e) {
            model.addAttribute("error", "Cannot divide by zero");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred");
        }
        return "index";
    }
}