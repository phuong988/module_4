package com.example.app_borrow_book.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private int visitCount = 0;

    @Before("execution(* com.example.app_borrow_book.controller.*.*(..))")
    public void logVisitCount() {
        visitCount++;
        System.out.println("Visit Count: " + visitCount);
    }

    @After("execution(* com.example.app_borrow_book.service.IBookService.borrowBook(..)) || execution(* com.example.library.service.BookServiceImpl.returnBook(..))")
    public void logBookStateChange() {
        System.out.println("Book state has been updated.");
    }

}

