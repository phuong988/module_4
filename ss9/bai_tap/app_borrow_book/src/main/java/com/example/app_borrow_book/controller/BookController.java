package com.example.app_borrow_book.controller;

import com.example.app_borrow_book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping()
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book/detail";
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable int id, Model model) {
        String borrowCode = bookService.borrowBook(id);
        model.addAttribute("message", "Borrowed successfully. Borrow Code: " + borrowCode);
        return "book/success";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, Model model) {
        bookService.returnBook(borrowCode);
        model.addAttribute("message", "Book returned successfully.");
        return "book/success";
    }

}
