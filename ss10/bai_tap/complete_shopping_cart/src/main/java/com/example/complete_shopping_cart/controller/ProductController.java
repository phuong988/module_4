package com.example.complete_shopping_cart.controller;

import com.example.complete_shopping_cart.exception.ProductNotFoundException;
import com.example.complete_shopping_cart.model.Product;
import com.example.complete_shopping_cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable int id, Model model) {
        // Thay thế RuntimeException bằng ProductNotFoundException
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        if (!productService.getProductById(product.getId()).isPresent()) {
            throw new ProductNotFoundException("Product with ID " + product.getId() + " not found for update");
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        productService.deleteProduct(product.getId());
        return "redirect:/products";
    }
}
