package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(cacheNames = "user", key = "#root.methodName+'['+#id+']'")
    public User getUser(int id) {
        System.out.println("查询");
        User user = userMapper.getUserById(id);
        return user;
    }

    @CachePut(value = "user", key = "'getUser'+'['+#user.id+']'")
    public User update(User user) {
        System.out.println("update");
        userMapper.updateUser(user);
        return user;
    }
}
