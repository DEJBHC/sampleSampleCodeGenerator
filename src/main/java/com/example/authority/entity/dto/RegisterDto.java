package com.example.authority.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: authority-v3.0.0
 * @ClassName:RegisterDto
 * @description: 注册使用的实体Dto
 * @author:dyy
 * @Version 3.0
 **/
@Data
@Builder
@Schema(title = "注册Dto", description = "注册")
public class RegisterDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(title = "序号")
    private Long id;
    @Schema(title = "账户名")
    private String username;
    @Schema(title = "密码")
    @TableField("password")
    private String password;
    @Schema(title = "确认密码")
    private String checkPass;
    @Schema(title = "昵称")
    private String nickname;
    @Schema(title = "头像")
    private String avatar;
    @Schema(title = "手机号")
    private String phone;
    @Schema(title = "邮箱")
    private String email;
    @Schema(title = "个人简介")
    private String content;
    @Schema(title = "令牌")
    private String token;
    @Schema(title = "验证码")
    private String code;




}