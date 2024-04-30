package com.example.authority.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: authority-v3.0.0
 * @ClassName:LoginDto
 * @description: 接收登录数据的实体类
 * @author:dyy
 * @Version 3.0
 **/
@Data
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String code;
    private String token;

}