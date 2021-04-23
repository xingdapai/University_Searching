package com.fan.backend.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.fan.backend.model.User;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserByUsername(@Param("username") String username);


}