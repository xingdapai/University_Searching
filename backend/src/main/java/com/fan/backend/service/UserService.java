package com.fan.backend.service;


import com.fan.backend.mapper.UserMapper;
import com.fan.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;

    public int register(User user) {

        User getUserByUserName = userMapper.getUserByUsername(user.getUserName());

        if (getUserByUserName != null) {
            return 1;
        }

        return 0;
    }


}
