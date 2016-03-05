package com.chengp.controller;

import com.chengp.entity.User;
import com.chengp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by pc on 3/2/16.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

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

        if (userService.findOne(user.getUsername()) != null) {

            model.addAttribute("error",true);
            model.addAttribute("valid", true);
            return "index";
        }
        if(result.hasErrors()){

            model.addAttribute("valid", true);
            return "index";
        }

        userService.setUserRoleAndEnabled(user, true, "USER");

        userService.save(user);

        userService.setAuth(user,request);


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

    @RequestMapping("/test")
    @ResponseBody
    public String showTest(Principal principal) {
        return principal.getName();
    }

}
