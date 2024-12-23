package com.example.app_blog.controller;

import com.example.app_blog.model.Blog;
import com.example.app_blog.service.IBlogService;
import com.example.app_blog.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@Validated @ModelAttribute Blog blog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "blog/create";
        }
        blogService.createBlog(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String detailBlog(@PathVariable int id, Model model) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable int id, @Validated @ModelAttribute Blog blog, BindingResult result) {
        if (result.hasErrors()) {
            return "blog/edit";
        }
        blogService.updateBlog(id, blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
}
