package com.chengp.controller;

import com.chengp.entity.Blog;
import com.chengp.entity.Hero;
import com.chengp.entity.SteamMessage;
import com.chengp.entity.User;
import com.chengp.service.*;
import com.chengp.util.WebUtil;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by pc on 3/2/16.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private Dota2Service dota2Service;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RssService rssService;

    @ModelAttribute("blog")
    public Blog constructBlog() {
        return new Blog();
    }


    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public String doRegister(@Valid User user, BindingResult result, Model model
            , HttpServletRequest request) {

        if(userService.findOne(user)){

            model.addAttribute("error", true);
            model.addAttribute("valid", true);
            return "index";
        }

        if (result.hasErrors()) {

            model.addAttribute("valid", true);
            return "index";
        }

        userService.setUserRoleAndEnabled(user, true, "ROLE_USER");

        userService.save(user);

        userService.setAuth(user, request);


        model.addAttribute("user", user);

        return "redirect:/account/blog";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "account/login";
    }

    @RequestMapping("/account/hero")
    public String showAccount(Model model, Principal principal) {

        model.addAttribute("username", principal.getName());
        return "account/index";
    }

    @RequestMapping("/update/heroes")
    @ResponseBody
    public String updateHero() {

        dota2Service.updateHeroes2DB();
        return "successed";
    }

    @RequestMapping("/dota2/heroes")
    @ResponseBody
    public List<Hero> showHeroes() {

        return dota2Service.getHeroes();
    }

    @RequestMapping(value = "/account/register/available")
    @ResponseBody
    public String userAvailable(@RequestParam String username) {

        return String.valueOf(!userService.findOne(username).isPresent());
    }

    @RequestMapping("/account/blog")
    public String showBlogFromFeed(Model model, Principal principal) {

        blogService.findFirstOne(principal.getName())
                .ifPresent(blog -> model.addAttribute("blog", blog));

        List<Blog> blogs = blogService.findAll(principal.getName());

        model.addAttribute("blogs", blogs);

        return "blog/index";
    }

    @RequestMapping("/account/blog/{id}")
    public String showBlogFromFeedById(@PathVariable("id") Integer id, Model model, Principal principal) {

        String username = principal.getName();
        blogService.findOne(username, id).ifPresent(blog -> model.addAttribute("blog", blog));

        List<Blog> blogs = blogService.findAll(principal.getName());

        model.addAttribute("blogs", blogs);

        return "blog/index";
    }

    @RequestMapping("/update/blog")
    public @ResponseBody String updateBlog() {

        /*rssService.loadFeedByURL("http://blog.dota2.com/feed/");*/

        return "succeed";
    }

    @RequestMapping("/account/subscribe")
    public String subscribe(Principal principal, Model model) {

        String username = principal.getName();

        List<Blog> blogs = blogService.findAll(username);

        model.addAttribute("username", principal.getName());
        model.addAttribute("blogs", blogs);

        return "blog/subscribe";
    }

    @RequestMapping("/account/subscribe/add/{urlNumber}")
    public String addSubscribe(@PathVariable("urlNumber") Integer urlNumber, Principal principal){

        User user = userService.findOne(principal.getName()).get();

        String url = WebUtil.findURL(urlNumber);

        Blog blog = rssService.loadFeedByURL(url, user);
        blog.setUrl(urlNumber);

        blogService.save(blog);

        return "redirect:/account/subscribe";
    }

    @RequestMapping("/account/subscribe/remove/{urlNumber}")
    public String removeSubscribe(@PathVariable("urlNumber") Integer urlNumber, Principal principal){

        User user = userService.findOne(principal.getName()).get();

        blogService.delete(urlNumber,user);

        return "redirect:/account/subscribe";
    }
}
