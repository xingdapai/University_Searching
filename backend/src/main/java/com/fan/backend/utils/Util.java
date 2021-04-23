package com.fan.backend.utils;

import com.fan.backend.model.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class Util {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
