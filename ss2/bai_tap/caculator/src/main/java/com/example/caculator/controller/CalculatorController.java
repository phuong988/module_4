package com.example.caculator.controller;

import com.example.caculator.service.CalculatorService;
import exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public String calculate(@RequestParam String num1,
                            @RequestParam String num2,
                            @RequestParam String operation,
                            Model model) {
        try {
            double parsedNum1 = calculatorService.parseDouble(num1);
            double parsedNum2 = calculatorService.parseDouble(num2);
            double result = calculatorService.calculate(parsedNum1, parsedNum2, operation);
            model.addAttribute("result", result);
        } catch (InvalidInputException e) {
            model.addAttribute("error", "Vui lòng nhập số hợp lệ");
        } catch (ArithmeticException e) {
            model.addAttribute("error", "Không thể chia cho 0");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi không mong muốn");
        }
        return "index";
    }
    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInputException(InvalidInputException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "index";
    }
}