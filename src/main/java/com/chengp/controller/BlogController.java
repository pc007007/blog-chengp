package com.chengp.controller;

import com.chengp.entity.Blog;
import com.chengp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by pc on 3/11/16.
 */
@Controller
@RequestMapping("/account/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("")
    public String showBlogFromFeed(Model model, Principal principal) {

        blogService.findFirstOne(principal.getName())
                .ifPresent(blog -> model.addAttribute("blog", blog));

        List<Blog> blogs = blogService.findAll(principal.getName());

        model.addAttribute("blogs", blogs);

        return "blog/index";
    }

    @RequestMapping("/{id}")
    public String showBlogFromFeedById(@PathVariable("id") Integer id, Model model, Principal principal) {

        String username = principal.getName();
        blogService.findOne(username, id).ifPresent(blog -> model.addAttribute("blog", blog));

        List<Blog> blogs = blogService.findAll(principal.getName());

        model.addAttribute("blogs", blogs);

        return "blog/index";
    }
}
