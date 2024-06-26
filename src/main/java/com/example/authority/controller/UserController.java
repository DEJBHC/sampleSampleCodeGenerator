package com.example.authority.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.annotations.ClearPerms;
import com.example.authority.common.Constant;
import com.example.authority.common.Result;
import com.example.authority.entity.User;
import com.example.authority.entity.UserRole;
import com.example.authority.service.UserRoleService;
import com.example.authority.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @program: authority-v3.0.0
 * @ClassName:UserController
 * @description: UserController前端控制器
 * @author:dyy
 * @Version 3.0
 **/
@Tag(name = "用户表 前端控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result save(@RequestBody User user) {
        if (userService.exists(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()))) {
            return Result.error("此账户名已经存在，请更换！");
        }
        user.setPassword(passwordEncoder.encode(Constant.DEFAULT_PASSWORD));
        userService.save(user);
        this.saveBatchUserRoles(user);

        return Result.success();
    }

    /**
     * 批量新增userRole
     *
     * @param user
     */
    private void saveBatchUserRoles(User user
    ) {
        user.getRoleIds().forEach(rid -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(rid);
            userRoleService.save(userRole);
        });
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody User user) {

//        先删除掉此用户所有的roleIds，然后再添加前端传递过来的数据、
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, id));
        this.saveBatchUserRoles(user);
        return Result.success(userService.updateById(user));
    }
    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @ClearPerms(value = true)
    @Operation(summary = "修改个人信息")
    @PutMapping
    public Result updateInformation( @RequestBody User user) {

//        先删除掉此用户所有的roleIds，然后再添加前端传递过来的数据、

        return Result.success(userService.updateById(user));
    }
    /**
     * 查询所有User
     *
     * @return
     */
    @Operation(summary = "查询所有User")

    @GetMapping
    public Result indAll() {
        return Result.success(userService.list());
    }

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    @Operation(summary = "获取单个")
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.setRoleIds(userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId())).stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        return Result.success(user);
    }

    /**
     * 分页显示
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Operation(summary = "分页显示")
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        return Result.success(userService.page(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<User>().like(User::getUsername, name).orderByDesc(User::getId)));
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @Operation(summary = "单个删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        removeUserAndLogout(id);
        return Result.success(userService.removeById(id));
    }

    /**
     * 删除和下线用户
     *
     * @param id
     */
    private void removeUserAndLogout(Object id) {
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, id));
        StpUtil.logout(id, "PC");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Operation(summary = "批量删除")
    @DeleteMapping("/batch/{ids}")
    @Transactional
    public Result deleteByIds(@PathVariable String[] ids) {
        Arrays.asList(ids).forEach(id -> removeUserAndLogout(id));
        return Result.success(userService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 批量导出
     * @param ids
     * @param response
     * @throws IOException
     */
    @Operation(summary = "批量导出")
    @GetMapping("/batch/export/{ids}")
    public void exportByIds(@PathVariable String[] ids, HttpServletResponse response) throws IOException {
        List<User> list = userService.listByIds(Arrays.asList(ids));
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("user导出数据", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), User.class).sheet("sheel1").doWrite(list);

    }
}

