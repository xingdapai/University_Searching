package com.fan.backend.controller;

import com.fan.backend.model.ResponseBean;
import com.fan.backend.model.User;
import com.fan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/login_error")
    public ResponseBean loginError() {
        return new ResponseBean("error", "登录失败!");
    }

    @RequestMapping("/login_success")
    public ResponseBean loginSuccess() {
        return new ResponseBean("success", "登录成功!");
    }

    @RequestMapping("/login_page")
    public ResponseBean loginPage() {
        return new ResponseBean("error", "尚未登录，请登录!");
    }

    @PostMapping("/register")
    public ResponseBean regist(User user) {

        int result = userService.register(user);

        if (result == 0) {
            return new ResponseBean("success", "注册成功!");
        } else if (result == 1) {
            return new ResponseBean("error", "用户名重复，注册失败!");
        } else {
            return new ResponseBean("error", "注册失败!");
        }

    }


}
