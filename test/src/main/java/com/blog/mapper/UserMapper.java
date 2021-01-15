package com.blog.mapper;

import com.blog.bean.UserBean;

public interface UserMapper {

    UserBean getInfo(String name,String password);
}
