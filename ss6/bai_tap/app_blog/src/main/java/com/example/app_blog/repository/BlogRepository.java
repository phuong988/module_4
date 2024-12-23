package com.example.app_blog.repository;

import com.example.app_blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
