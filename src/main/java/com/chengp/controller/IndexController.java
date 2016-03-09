package com.chengp.controller;

import com.chengp.entity.Blog;
import com.chengp.entity.Hero;
import com.chengp.entity.SteamMessage;
import com.chengp.entity.User;
import com.chengp.service.BlogService;
import com.chengp.service.Dota2Service;
import com.chengp.service.ItemService;
import com.chengp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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

        return "redirect:/account";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "account/login";
    }

    @RequestMapping("/account")
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
    public String showBlogFromFeed(Model model) {

        Blog blog = blogService.findOneByTitle("Dota 2");

        blog.setItems(itemService.findItems(blog));

        model.addAttribute("blog", blog);


        return "blog/index";
    }

    @RequestMapping("/update/blog")
    public @ResponseBody String updateBlog() {

        blogService.loadFeedByURL("http://blog.dota2.com/feed/");

        return "succeed";
    }

}
