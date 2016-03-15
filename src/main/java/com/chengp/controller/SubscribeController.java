package com.chengp.controller;

import com.chengp.entity.Blog;
import com.chengp.entity.User;
import com.chengp.service.BlogService;
import com.chengp.service.RssService;
import com.chengp.service.UserService;
import com.chengp.util.WebUtil;
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
@RequestMapping("/account/subscribe")
public class SubscribeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RssService rssService;

    @Autowired
    private BlogService blogService;


    @RequestMapping("")
    public String subscribe(Principal principal, Model model) {

        String username = principal.getName();

        List<Blog> blogs = blogService.findAll(username);

        model.addAttribute("username", principal.getName());
        model.addAttribute("blogs", blogs);

        return "blog/subscribe";
    }

    @RequestMapping("/add/{urlNumber}")
    public String addSubscribe(@PathVariable("urlNumber") Integer urlNumber, Principal principal){

        User user = userService.findOne(principal.getName()).get();

        String url = WebUtil.findURL(urlNumber);

        Blog blog = rssService.loadFeedByURL(url, user);
        blog.setUrl(urlNumber);

        blogService.save(blog);

        return "redirect:/account/subscribe";
    }

    @RequestMapping("/remove/{urlNumber}")
    public String removeSubscribe(@PathVariable("urlNumber") Integer urlNumber, Principal principal){

        User user = userService.findOne(principal.getName()).get();

        blogService.delete(urlNumber,user);

        return "redirect:/account/subscribe";
    }
}
