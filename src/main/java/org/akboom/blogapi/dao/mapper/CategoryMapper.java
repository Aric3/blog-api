package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname CategoryMapper
 * @Description 目录mapper
 * @Author AoLinChen
 */
@Mapper
public interface CategoryMapper {

   List<CategoryVo> selectCategories();
}
