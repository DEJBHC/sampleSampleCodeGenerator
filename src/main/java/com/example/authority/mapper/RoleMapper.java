package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-10-01
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
