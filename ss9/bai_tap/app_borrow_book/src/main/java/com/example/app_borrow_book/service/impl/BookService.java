package com.example.app_borrow_book.service.impl;

import com.example.app_borrow_book.exception.BookNotAvailableException;
import com.example.app_borrow_book.exception.InvalidBorrowCodeException;
import com.example.app_borrow_book.model.Book;
import com.example.app_borrow_book.model.BorrowBook;
import com.example.app_borrow_book.repository.BookRepository;
import com.example.app_borrow_book.repository.BorrowCodeRepository;
import com.example.app_borrow_book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowCodeRepository borrowCodeRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public String borrowBook(int bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Book is not available for borrowing");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        String borrowCode = String.format("%05d", new Random().nextInt(100000));

        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setBorrowCode(borrowCode);
        borrowBook.setBook(book);
        borrowCodeRepository.save(borrowBook);

        return borrowCode;
    }

    @Override
    public void returnBook(String borrowCode) {
        BorrowBook borrowBook = borrowCodeRepository.findByBorrowCode(borrowCode)
                .orElseThrow(() -> new InvalidBorrowCodeException("Invalid borrow code"));

        Book book = borrowBook.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        borrowCodeRepository.delete(borrowBook);
    }
}
