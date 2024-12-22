package com.example.product_management.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleProductNotFoundException(ProductNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }
}
