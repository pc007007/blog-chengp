package com.chengp.controller;

import com.chengp.entity.User;
import com.chengp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 3/2/16.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
    public String doRegister(@ModelAttribute User user, Model model, HttpServletRequest request) {

        userService.save(user);
        model.addAttribute("user", user);

        setAuth(user,request);

        return "redirect:/index";
    }

    public void setAuth(User user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
