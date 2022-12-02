package org.akboom.blogapi.controller;

import org.akboom.blogapi.aop.Cache;
import org.akboom.blogapi.service.CategoryService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Cache(expire = 30 * 60 * 1000,name = "getAllCategory")
    public Result getAllCategory() {
        return categoryService.getCategories();
    }

    @GetMapping("detail")
    @Cache(expire = 30 * 60 * 1000,name = "getAllCategoryDetails")
    public Result getAllCategoryDetails(){
        return categoryService.getCategoryDetails();
    }

    @GetMapping("detail/{id}")
    @Cache(expire = 30 * 60 * 1000,name = "getCategoryDetailById")
    public Result getCategoryDetailById(@PathVariable Long id){
        return categoryService.getCategoryDetailById(id);
    }
}
