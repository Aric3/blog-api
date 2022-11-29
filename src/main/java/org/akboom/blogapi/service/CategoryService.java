package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.CategoryVo;
import org.akboom.blogapi.vo.Result;

import java.util.List;

/**
 * @Classname CategoryService
 * @Description 目录业务逻辑接口
 * @Author AoLinChen
 */
public interface CategoryService {

    Result getCategories();
}
