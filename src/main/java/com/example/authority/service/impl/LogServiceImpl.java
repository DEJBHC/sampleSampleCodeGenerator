package com.example.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.Log;
import com.example.authority.mapper.LogMapper;
import com.example.authority.service.LogService;
import org.springframework.stereotype.Service;


/**
 * @program: authority-v3.0.0
 * @ClassName:LogServiceImpl
 * @description: 操作日志 service实现类
 * @author:dyy
 * @Version 3.0
 **/
@Service
        public class LogServiceImpl extends ServiceImpl<LogMapper, Log>implements LogService {

        }
