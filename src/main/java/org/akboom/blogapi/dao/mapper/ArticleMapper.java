package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname ArticleMapper
 * @Description Article相关的mapper类
 * @Author AoLinChen
 */
@Mapper
public interface ArticleMapper {

    /**
     * @description 分页查询文章列表
     * @param offset
     * @param pageSize
     * @return Articles
     */

    List<Article> selectArticleByPageParam(@Param("offset")int offset, @Param("pageSize")int pageSize);
}