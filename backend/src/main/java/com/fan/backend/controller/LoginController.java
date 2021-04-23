package com.fan.backend.controller;

import com.fan.backend.model.ResponseBean;
import com.fan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {

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

}
