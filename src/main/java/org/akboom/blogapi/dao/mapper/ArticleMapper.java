package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.dos.Archive;
import org.akboom.blogapi.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname ArticleMapper
 * @Description Article相关的mapper类
 * @Author AoLinChen
 */
@Mapper
public interface ArticleMapper {

    /**
     * @param offset
     * @param pageSize
     * @return Articles
     * @description 分页查询文章列表
     */

    List<ArticleVo> selectArticleByPageParam(@Param("offset")int offset, @Param("pageSize")int pageSize);
    /**
     * @Description 获得view_counts最高的limit篇文章
     * @param limit
     * @return id title组成的二元组数组
     */

    List<HashMap<String,Object>> selectHotArticle(@Param("limit") int limit);

    List<HashMap<String, Object>> selectNewArticle(@Param("limit") int limit);

    List<Archive> selectArchive();
}