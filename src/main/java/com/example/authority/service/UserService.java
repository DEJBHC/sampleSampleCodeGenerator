package com.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authority.entity.User;


/**
 * @program: authority-v3.0.0
 * @ClassName: UserService
 * @description: 用户表 service
 * @author:dyy
 * @Version 3.0
 **/

public interface UserService extends IService<User> {

    User getUserInfo();
}
