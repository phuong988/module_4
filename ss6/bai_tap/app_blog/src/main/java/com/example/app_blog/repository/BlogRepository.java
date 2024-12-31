package com.example.app_blog.repository;

import com.example.app_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    List<Blog> findByTitleContainingIgnoreCase(String keyword);

    Page<Blog> findByCategoryId(Integer categoryId, Pageable pageable);
}
