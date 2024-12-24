package com.example.product_management.controller;

import com.example.product_management.model.Product;
import com.example.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView listProducts(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Product> productSearch;
        if(keyword != null && !keyword.isEmpty()){
            productSearch = productService.searchByName(keyword);
        } else {
            productSearch = productService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", productSearch);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/create")
    public String createProduct(@Validated @ModelAttribute Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/create";
        }
        try {
            productService.save(product);
            redirectAttributes.addFlashAttribute("message", "Create product successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            model.addAttribute("product", productService.findById(id));
            return "product/edit";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "product/list";
        }
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable int id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        boolean updated = productService.update(id, product);
        if (updated) {
            redirectAttributes.addFlashAttribute("message", "Update product successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update product. Please try again.");
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id, RedirectAttributes redirectAttributes) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Delete product successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete product. Please try again.");
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/detail")
    public String showProductDetail(@PathVariable int id, Model model) {
        try {
            model.addAttribute("product", productService.findById(id));
            return "product/detail";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "product/list";
        }
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String keyword) {
        return "redirect:/products?keyword=" + keyword;
    }
}