package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.ArticleMapper;
import org.akboom.blogapi.dao.pojo.Article;
import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.vo.ArticleVo;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.PageParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @param pageParam
     * @return
     * @description 分页查询文章列表
     */
    @Override
    public Result getArticleList(PageParam pageParam) {

        int offset = (pageParam.getPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<Article> articles = articleMapper.selectArticleByPageParam(offset, pageSize);
        List<ArticleVo> articleVoList = copyList(articles);
        return Result.success(articleVoList);
    }

    private List<ArticleVo> copyList(List<Article> articles) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articles) {
            articleVoList.add(copy(article));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article){

        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        return articleVo;

    }
}
