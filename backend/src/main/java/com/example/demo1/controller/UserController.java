package com.example.demo1.controller;


import com.example.demo1.service.UserService;
import com.example.demo1.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/api/login/")
    public Integer currentUserId() {
        return Util.getCurrentUser().getUserId();
    }

    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getUserEmail();
    }

}