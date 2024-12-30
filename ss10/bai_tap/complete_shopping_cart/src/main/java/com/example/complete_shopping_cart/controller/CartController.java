package com.example.complete_shopping_cart.controller;

import com.example.complete_shopping_cart.exception.ProductNotFoundException;
import com.example.complete_shopping_cart.model.Product;
import com.example.complete_shopping_cart.service.ICartService;
import com.example.complete_shopping_cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        Map<Product, Integer> cartItems = cartService.getCartItems();
        model.addAttribute("cart", new HashMap<String, Object>() {{
            put("items", cartItems);
        }});
        model.addAttribute("totalPrice", cartService.getTotal());
        return "product/cart/view";
    }

    @PostMapping("/{productId}/add")
    public String addToCart(@PathVariable int productId) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        cartService.addToCart(product);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable int productId) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        cartService.removeFromCart(product);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable int productId, @RequestParam int quantity) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        cartService.updateQuantity(product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Map<Product, Integer> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartService.getTotal());
        return "cart/checkout";
    }

    @GetMapping("/item/{productId}")
    public String viewCartItem(@PathVariable int productId, Model model) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        int quantity = cartService.getQuantity(product);
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        return "/product/cart/item";
    }
}