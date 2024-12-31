package com.example.app_blog.controller;

import com.example.app_blog.model.Blog;
import com.example.app_blog.service.IBlogService;
import com.example.app_blog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listBlogs(Model model,
                            @RequestParam(required = false) Integer categoryId,
                            @RequestParam(required = false) String keyword) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        return "blog/list";
    }

    @GetMapping("/search")
    public String searchBlogs(@RequestParam String keyword, Model model,
                              @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Blog> blogs = blogService.searchBlogs(keyword, pageable);
        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@Validated @ModelAttribute Blog blog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getAllErrors());
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
        model.addAttribute("categories", categoryService.getAllCategories());
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
