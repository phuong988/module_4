package com.example.complete_shopping_cart.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/product_error";
    }
    @ExceptionHandler(CartException.class)
    public String handleCartException(CartException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/cart-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", "Something went wrong!");
        return "error/general-error";
    }
}
