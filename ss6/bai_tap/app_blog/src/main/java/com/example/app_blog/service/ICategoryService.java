package com.example.app_blog.service;

import com.example.app_blog.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category updateCategory(Integer id, Category updatedCategory);
    void deleteCategory(Integer id);
}
