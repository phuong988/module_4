package com.example.complete_shopping_cart.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
    @After("execution(* com.example.complete_shopping_cart.controller.CartController.addToCart(..))")
    public void logAddToCart() {
        System.out.println("A product was added to the cart.");
    }

    @After("execution(* com.example.complete_shopping_cart.controller.CartController.removeFromCart(..))")
    public void logRemoveFromCart() {
        System.out.println("A product was removed from the cart.");
    }

    @After("execution(* com.example.complete_shopping_cart.controller.CartController.checkout(..))")
    public void logCheckout() {
        System.out.println("A checkout operation was performed.");
    }
}
