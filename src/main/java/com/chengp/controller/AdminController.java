package com.chengp.controller;

import com.chengp.entity.User;
import com.chengp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by pc on 3/7/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getusers")
    public List<User> showUser() {

        return userService.findAll();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String username) {

        this.validateUser(username);
        return userService.findOne(username).map(user -> {
                    userService.delete(user);
                    HttpHeaders httpHeaders = new HttpHeaders();
                    return new ResponseEntity<User>(null, httpHeaders, HttpStatus.ACCEPTED);
                }
        ).get();
    }

/*    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public User findUser(@PathVariable String username) {

        this.validateUser(username);
        return userService.findOne(username).get();
    }*/


    private void validateUser(String username) {
        this.userService.findOne(username).orElseThrow(
                () -> new UserNotFoundException(username));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String username) {
            super("could not find user '" + username + "'.");
        }
    }
}