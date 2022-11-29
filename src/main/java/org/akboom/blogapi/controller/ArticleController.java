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
 * @Description 处理文章相关请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * @Description 获取文章列表
     * @param pageParam
     * @return Result
     */
    @PostMapping
    public Result getArticleList(@RequestBody PageParam pageParam){

        return articleService.getArticleList(pageParam);
    }
    /**
     * @Description 获取最热文章
     * @param
     * @return Result
     */
    @PostMapping("hot")
    public Result getHotArticle(){

        return articleService.getHotArticle();
    }
    /**
     * @Description 获取最新文章
     * @param
     * @return Result
     */

    @PostMapping("new")
    public Result getNewArticle(){
        return articleService.getNewArticle();
    }
    /**
     * @Description 获取文章归档信息
     * @param
     * @return Result
     */

    @PostMapping("listArchives")
    public Result getArchives(){
        return articleService.getArchives();
    }

}