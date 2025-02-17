package com.example.app_blog.service.impl;

import com.example.app_blog.model.Blog;
import com.example.app_blog.repository.BlogRepository;
import com.example.app_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public Blog updateBlog(Integer id, Blog updatedBlog) {
        return blogRepository.findById(id)
                .map(existingBlog -> {
                    existingBlog.setTitle(updatedBlog.getTitle());
                    existingBlog.setContent(updatedBlog.getContent());
                    existingBlog.setAuthor(updatedBlog.getAuthor());
                    existingBlog.setCategory(updatedBlog.getCategory());
                    return blogRepository.save(existingBlog);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + id));
    }

    @Override
    public void deleteBlog(Integer id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id: " + id);
        }
    }

    @Override
    public Page<Blog> getAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> searchBlog(String keyword) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public Page<Blog> searchBlogs(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }
    @Override
    public Page<Blog> getBlogsByCategory(Integer categoryId, Pageable pageable) {
        return blogRepository.findByCategoryId(categoryId, pageable);
    }

}
