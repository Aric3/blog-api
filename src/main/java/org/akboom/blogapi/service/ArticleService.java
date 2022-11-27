package org.akboom.blogapi.service;


import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.PageParam;
import org.springframework.stereotype.Service;

public interface ArticleService {

    /**
     * @description 分页查询文章列表
     * @param pageParam
     * @return Result
     */

    public Result getArticleList(PageParam pageParam);
}
