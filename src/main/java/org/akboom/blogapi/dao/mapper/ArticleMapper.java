package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.dos.Archive;
import org.akboom.blogapi.dao.pojo.Article;
import org.akboom.blogapi.vo.ArticleSearchVo;
import org.akboom.blogapi.vo.ArticleVo;
import org.akboom.blogapi.vo.param.PageParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ArticleMapper
 * @Description 文章相关mapper
 * @Author AoLinChen
 */
@Mapper
public interface ArticleMapper {

    /**
     * @param pageParam
     * @return Articles
     * @description 分页查询文章列表
     */

    List<ArticleVo> selectArticleByPageParam(PageParam pageParam);

    /**
     * @param limit
     * @return id title组成的二元组数组
     * @Description 获得view_counts最高的limit篇文章
     */

    List<HashMap<String, Object>> selectHotArticle(@Param("limit") int limit);

    List<HashMap<String, Object>> selectNewArticle(@Param("limit") int limit);

    List<Archive> selectArchive();

    ArticleVo selectArticleById(@Param("id") long id);

    int updateViewCountsById(@Param("viewCountsNew") int viewCountsNew,@Param("viewCountsOld")int viewCountsOld, @Param("articleId") long articleId);


    Integer selectViewCountsById(@Param("articleId")long articleId);

    int insertArticle(Article article);

    int selectCommentCountsById(@Param("articleId")Long articleId);

    int updateCommentCountsById(@Param("newCounts")int newCounts,@Param("oldCounts")int oldCounts,@Param("articleId") Long articleId);

    List<ArticleSearchVo>selectArticlesByWord(@Param("search")String search);

    int updateArticle(Article article);
}