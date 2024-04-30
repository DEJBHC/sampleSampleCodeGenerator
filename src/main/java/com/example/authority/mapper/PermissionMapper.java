package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-10-01
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
