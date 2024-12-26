package com.example.app_borrow_book.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="Books"  )
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]{3,50}$", message = "Title must be between 3 and 50 characters long and contain only letters and spaces")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int quantity;
}
