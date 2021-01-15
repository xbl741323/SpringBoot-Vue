package com.blog.serviceImpl;

import com.blog.bean.UserBean;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // 将DAO注入Service层
    @Autowired(required = false)
    private UserMapper userMapper;

    public UserBean loginIn(String name, String password){

        return userMapper.getInfo(name,password);
    }
}
