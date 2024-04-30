package com.example.authority.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: authority-v3.0.0
 * @ClassName:SqlDto
 * @description: SQL语句构建类
 * @author:dyy
 * @Version 3.0
 **/
@Data
@Accessors(chain = true)
public class SqlDto implements Serializable {
    private static final long serialVersionUID = 1L;
//    名称
    private String name;
//    字段类型和常用长度
    private String  typeAndsize;
//    是否必选
    private Boolean isRequire=false;
//    注释描述
    private String  description;
//  默认值
    private String  defaultValue;
//    表单组件
    private String  formComponent;
}