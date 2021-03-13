package com.example.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表
 前端控制器
 * </p>
 *
 * @author yxx
 * @since 2021-02-21
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user = userService.getById("1");
        return Result.succ(user);
    }

    @GetMapping("/users/{userId}")
    public Result detail(@PathVariable(name = "userId") Long userId){
        User user = userService.getById(userId);
        Assert.notNull(user, "该用户被删除了");
        return Result.succ(user);
    }

    @GetMapping("/users")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 6);
        IPage pageData = userService.page(page, new QueryWrapper<User>());
        return Result.succ(pageData);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.succ(user);
    }

    @PostMapping("/users/delete")
    public Result delete(@RequestBody User user){
        userService.removeById(user.getUserId());
        return Result.succ(null);
    }

    @GetMapping("/searchUser")
    public Result search(@RequestParam(defaultValue = "1") Integer currentPage, String info){
        Page page = new Page(currentPage, 100);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("user_name", info)
                .or()
                .like("user_phone", info);
        IPage pageData = userService.page(page, queryWrapper);
        return Result.succ(pageData);
    }
}
