package com.example.app_blog.service;

import com.example.app_blog.model.Blog;

import java.util.List;

public interface IService<Blog> {
    Blog createBlog(Blog blog);
    List<Blog> getAllBlogs();
    Blog getBlogById(Integer id);
    Blog updateBlog(Integer id, Blog updatedBlog);
    void deleteBlog(Integer id);
}
