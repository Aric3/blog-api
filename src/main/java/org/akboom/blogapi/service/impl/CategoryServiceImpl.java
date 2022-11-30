package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.CategoryMapper;
import org.akboom.blogapi.dao.pojo.Category;
import org.akboom.blogapi.service.CategoryService;
import org.akboom.blogapi.vo.CategoryVo;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname CategoryServiceImpl
 * @Description 目录业务逻辑实现类
 * @Author AoLinChen
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Result getCategories() {
        List<CategoryVo> categories = categoryMapper.selectCategories();
        return Result.success(categories);
    }

    @Override
    public Result getCategoryDetails() {
       List<Category> categories = categoryMapper.selectCategoryDetails();
        return Result.success(categories);
    }

    @Override
    public Result getCategoryDetailById(Long id) {
        Category category  = categoryMapper.selectCategoryDetailById(id);
        return Result.success(category);
    }
}
