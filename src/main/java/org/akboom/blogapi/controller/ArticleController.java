package org.akboom.blogapi.controller;

import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ArticleController
 * @Description 处理有关Article请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * @description 分页获得文章列表
     * @param pageParam
     * @return
     */
    @PostMapping
    public Result getArticleList(@RequestBody PageParam pageParam){

        return articleService.getArticleList(pageParam);
    }

}