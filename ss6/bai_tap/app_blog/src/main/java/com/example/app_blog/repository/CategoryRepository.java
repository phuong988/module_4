package com.example.app_blog.repository;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Blog> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
