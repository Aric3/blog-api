package org.akboom.blogapi.controller;

import org.akboom.blogapi.util.UserThreadLocal;
import org.akboom.blogapi.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description 测试拦截器接口
 * @Author AoLinChen
 */
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping
    public Result test() {
        System.out.println(UserThreadLocal.get());
        return Result.success(null);
    }

}
