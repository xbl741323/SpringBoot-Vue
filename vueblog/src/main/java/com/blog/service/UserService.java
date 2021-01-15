package com.blog.service;

import com.blog.bean.UserBean;

public interface UserService {

    UserBean loginIn(String name, String password);

}
