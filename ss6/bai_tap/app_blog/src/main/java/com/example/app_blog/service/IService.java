package com.example.app_blog.service;

import com.example.app_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<Blog> {
    Blog createBlog(Blog blog);
    List<Blog> getAllBlogs();
    Blog getBlogById(Integer id);
    Blog updateBlog(Integer id, Blog updatedBlog);
    void deleteBlog(Integer id);
    Page<Blog> getAllBlogs(Pageable pageable);
    Page<Blog> searchBlogs(String keyword, Pageable pageable);
    Page<Blog> getBlogsByCategory(Integer categoryId, Pageable pageable);
    List<Blog> searchBlog(String keyword);
}
