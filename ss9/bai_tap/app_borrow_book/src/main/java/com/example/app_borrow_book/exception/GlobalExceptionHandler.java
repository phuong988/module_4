package com.example.app_borrow_book.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotAvailableException.class)
    public String handleBookNotAvailableException(BookNotAvailableException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/book_error";
    }
    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String handleInvalidBorrowCodeException(InvalidBorrowCodeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/borrow_code_error";
    }
}
