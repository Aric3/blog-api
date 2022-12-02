package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.ArticleBody;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ArticleBodyMapper.xml
 * @Description 文章主体mapper
 * @Author AoLinChen
 */
@Mapper
public interface ArticleBodyMapper {
    int insert(ArticleBody articleBody);

    int updateBodyByArticleId(ArticleBody articleBody);
}
