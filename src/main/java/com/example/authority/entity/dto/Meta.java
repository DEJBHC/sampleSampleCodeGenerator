package com.example.authority.entity.dto;

/**
 * @program: authority-v3.0.0
 * @ClassName:Meta
 * @description: 路由封装数据
 * @author:dyy
 * @Version 3.0
 **/


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 图标
     */
    private String icon;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否隐藏
     */
    private boolean hidden;
}