package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ArticleTagMapper
 * @Description 文章-标签关联mapper
 * @Author AoLinChen
 */
@Mapper
public interface ArticleTagMapper {
    void insert(ArticleTag articleTag);

    int deleteTagsByArticleId(Long articleId);
}
