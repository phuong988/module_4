package com.example.app_blog.controller;

import com.example.app_blog.model.Category;
import com.example.app_blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/blog/category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "/blog/category/create";
    }

    @PostMapping("/create")
    public String createCategory(@Validated @ModelAttribute Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "/blog/category/create";
        }
        categoryService.createCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/category";
        }
        model.addAttribute("category", category);
        return "/blog/category/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(@PathVariable int id, @Validated @ModelAttribute Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/edit";
        }
        categoryService.updateCategory(id, category);
        return "redirect:/category";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}

