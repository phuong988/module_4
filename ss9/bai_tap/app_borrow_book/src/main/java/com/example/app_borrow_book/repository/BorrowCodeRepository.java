package com.example.app_borrow_book.repository;

import com.example.app_borrow_book.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowCodeRepository extends JpaRepository<BorrowBook, Integer> {
    Optional<BorrowBook> findByBorrowCode(String borrowCode);


}
