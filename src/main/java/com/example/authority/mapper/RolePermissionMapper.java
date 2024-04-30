package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-09-27
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
