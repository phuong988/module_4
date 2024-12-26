package com.example.app_borrow_book.service;

import com.example.app_borrow_book.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Book saveBook(Book book);
    String borrowBook(int bookId);
    void returnBook(String borrowCode);
}
