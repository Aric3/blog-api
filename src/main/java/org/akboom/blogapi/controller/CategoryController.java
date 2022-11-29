package org.akboom.blogapi.controller;

import org.akboom.blogapi.service.CategoryService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname CategoryController
 * @Description 处理目录相关请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Result getAllCategory() {

    return categoryService.getCategories();
    }
}
