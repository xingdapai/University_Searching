package com.example.demo1.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.example.demo1.model.User;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserByUsername(@Param("username") String username);


}