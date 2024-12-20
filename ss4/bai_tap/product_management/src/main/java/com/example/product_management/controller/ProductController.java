package com.example.product_management.controller;

import com.example.product_management.model.Product;
import com.example.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        if(keyword != null &&!keyword.isEmpty()){
            productSearch = productService.searchByName(keyword);
        }else {
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
    public String createProduct(@ModelAttribute Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/create";
        }
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Create product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/edit";
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/detail")
    public String showProductDetail(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/detail";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String keyword) {
        return "redirect:/products?keyword=" + keyword;
    }
}
