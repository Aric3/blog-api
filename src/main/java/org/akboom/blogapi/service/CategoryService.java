package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.CategoryVo;
import org.akboom.blogapi.vo.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname CategoryService
 * @Description 目录业务逻辑接口
 * @Author AoLinChen
 */
@Transactional
public interface CategoryService {

    Result getCategories();

    Result getCategoryDetails();

    Result getCategoryDetailById(Long id);
}
