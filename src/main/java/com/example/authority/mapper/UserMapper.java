package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-09-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
