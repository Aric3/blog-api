package org.akboom.blogapi.controller;

import org.akboom.blogapi.aop.Cache;
import org.akboom.blogapi.aop.Logging;
import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.ArticleParam;
import org.akboom.blogapi.vo.param.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @param pageParam
     * @return Result
     * @Description 获取文章列表
     */
    @PostMapping
    @Logging(module = "article",operation = "获取文章列表")
    @Cache(expire = 1 * 60 * 1000,name = "getArticleList")
    public Result getArticleList(@RequestBody PageParam pageParam) {

        return articleService.getArticleList(pageParam);
    }

    /**
     * @param
     * @return Result
     * @Description 获取最热文章
     */
    @PostMapping("hot")
    public Result getHotArticle() {

        return articleService.getHotArticle();
    }

    /**
     * @param
     * @return Result
     * @Description 获取最新文章
     */

    @PostMapping("new")
    public Result getNewArticle() {
        return articleService.getNewArticle();
    }

    /**
     * @param
     * @return Result
     * @Description 获取文章归档信息
     */
    @PostMapping("listArchives")
    public Result getArchives() {
        return articleService.getArchives();
    }

    @PostMapping("view/{id}")
    public Result getArticle(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("publish")
    public Result publishArticle(@RequestBody ArticleParam articleParam) {
        return articleService.publishArticle(articleParam);
    }

    @PostMapping("search")
    public Result searchArticlesByWord(@RequestBody Map<String,String>map){
        String search = map.get("search");
        if (!StringUtils.isBlank(search)) {
            return articleService.searchArticlesByWord(search);
        }
        return Result.success(null);

    }
}