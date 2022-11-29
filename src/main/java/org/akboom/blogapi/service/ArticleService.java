package org.akboom.blogapi.service;


import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.PageParam;
import org.springframework.stereotype.Service;

public interface ArticleService {

    /**
     * @param pageParam
     * @return Result
     * @Description 分页查询文章列表
     */

    Result getArticleList(PageParam pageParam);

    /**
     * @param
     * @return Result
     * @Description 查询view_counts最多的limit个文章
     */
    Result getHotArticle();

    Result getNewArticle();

    Result getArchives();
}
