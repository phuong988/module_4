package com.example.app_borrow_book.exception;

public class InvalidBorrowCodeException extends RuntimeException  {
    public InvalidBorrowCodeException(String message) {
        super(message);
    }
}
