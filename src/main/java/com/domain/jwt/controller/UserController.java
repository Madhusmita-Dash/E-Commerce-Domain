package com.domain.jwt.controller;

import com.domain.jwt.entity.User;
import com.domain.jwt.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);

    }
    @GetMapping({"/forAdmin"})
    public String forAdmin(){
        return "This URL is only accessible to admin" ;
    }
    @GetMapping({"/forUser"})
    public String forUser(){
        return "This URL is only accessible to user" ;
    }
}
