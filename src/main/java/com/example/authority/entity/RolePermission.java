package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @program: authority-v3.0.0
 * @ClassName:RolePermission
 * @description: 角色菜单表 实体类
 * @author:dyy
 * @Version 3.0
 **/

@Data
@Accessors(chain = true)
@TableName("sys_role_permission")
@Schema(title = "RolePermission对象", description = "角色菜单表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(title = "角色id")
    @TableField("role_id")
    private Long roleId;

    @Schema(title = "菜单id")
    @TableField("permission_id")
    private Long permissionId;


}
