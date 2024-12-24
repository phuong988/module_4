package com.example.product_management.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleInvalidInputException(IllegalArgumentException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "product/list";
    }
}
