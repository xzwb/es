package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    /**
     * 删除缓存
     * allEntries是否删除catch中的所有缓存
     *  beforeInvocation:是否在方法之前执行 默认false
     * @param id
     */
    @CacheEvict(value = "user", key = "'getUser'+'['+#id+']'", allEntries = true)
    public void delete(int id) {
        System.out.println("delete");
        userMapper.deleteUser(id);
    }
}
