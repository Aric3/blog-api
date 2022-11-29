package org.akboom.blogapi.controller;

import org.akboom.blogapi.service.LoginService;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname RegisterController
 * @Description 注册相关请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }
}
