package org.akboom.blogapi.controller;

import org.akboom.blogapi.service.SysUserService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description 用户相关请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result getCurrentUser(@RequestHeader("Authorization")  String token){

    return sysUserService.getUserByToken(token);
    }
}
