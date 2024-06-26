package com.example.authority.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.common.Constant;
import com.example.authority.entity.User;
import com.example.authority.mapper.UserMapper;
import com.example.authority.service.UserService;
import com.example.authority.utils.RedisUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * @program: authority-v3.0.0
 * @ClassName:UserServiceImpl
 * @description: 用户表 service实现类
 * @author:dyy
 * @Version 3.0
 **/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 根据username获取User
     *
     * @param
     * @return
     */
    @Override
    public User getUserInfo() {
        if (ObjectUtil.isNotEmpty(redisUtils.hget(Constant.ONLINE_PREFIX, String.valueOf(StpUtil.getLoginId())))){
            log.info("从redis中获取onlineUser缓存信息");
            return (User) redisUtils.hget(Constant.ONLINE_PREFIX, String.valueOf(StpUtil.getLoginId()));
        }else{
            User onlineUser=this.getById((Serializable) StpUtil.getLoginId());
            redisUtils.hset(Constant.ONLINE_PREFIX, String.valueOf(StpUtil.getLoginId()),onlineUser);
            return onlineUser;
        }


    }

}
