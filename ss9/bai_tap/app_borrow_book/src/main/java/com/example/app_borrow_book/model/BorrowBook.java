package com.example.app_borrow_book.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "borrow_code"  )
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String borrowCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
}
