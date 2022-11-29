package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.dos.Archive;
import org.akboom.blogapi.dao.mapper.ArticleBodyMapper;
import org.akboom.blogapi.dao.mapper.ArticleMapper;
import org.akboom.blogapi.dao.mapper.ArticleTagMapper;
import org.akboom.blogapi.dao.pojo.Article;
import org.akboom.blogapi.dao.pojo.ArticleBody;
import org.akboom.blogapi.dao.pojo.ArticleTag;
import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.util.SnowFlakeUtils;
import org.akboom.blogapi.util.UserThreadLocal;
import org.akboom.blogapi.vo.ArticleVo;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.TagVo;
import org.akboom.blogapi.vo.param.ArticleParam;
import org.akboom.blogapi.vo.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ThreadService threadService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    /**
     * @param pageParam
     * @return
     * @description 分页查询文章列表
     */
    @Override
    public Result getArticleList(PageParam pageParam) {

        int offset = (pageParam.getPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<ArticleVo> articleVoList = articleMapper.selectArticleByPageParam(offset, pageSize);
        return Result.success(articleVoList);
    }

    /**
     * @param
     * @return Result
     * @Description 查询view_counts最多的limit个文章
     */
    @Override

    public Result getHotArticle() {
        int limit = 6;
        List<HashMap<String, Object>> data = articleMapper.selectHotArticle(limit);
        return Result.success(data);
    }


    @Override
    public Result getNewArticle() {
        int limit = 6;
        List<HashMap<String, Object>> data = articleMapper.selectNewArticle(limit);
        return Result.success(data);
    }

    /**
     * @param
     * @return Result
     * @Description 查询归档数据
     */
    @Override
    public Result getArchives() {
        List<Archive> archives = articleMapper.selectArchive();
        return Result.success(archives);
    }

    /**
     * @param id
     * @return Result
     * @Description 获取文章主体 同时更新文章阅读次数
     */
    @Override
    public Result getArticle(Long id) {
        ArticleVo article = articleMapper.selectArticleById(id);
        //将更新阅读次数的操作放到线程池当中执行 不影响主线程
        Long articleId = article.getId();
        threadService.updateArticleViewCounts(articleMapper, articleId, article.getViewCounts());
        return Result.success(article);
    }

    @Override
    public Result publishArticle(ArticleParam articleParam) {
        //获取当前用户对象
        SysUser sysUser = UserThreadLocal.get();

        Article article = new Article();
        Long articleId = SnowFlakeUtils.nextId();
        article.setId(articleId);
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        article.setCreateDate(System.currentTimeMillis());
        article.setCommentCounts(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(0);
        article.setBodyId(-1L);
        //插入到article表
        this.articleMapper.insertArticle(article);

        //添加到article_body关联表
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tag.getId());
                this.articleTagMapper.insert(articleTag);
            }
        }
        //添加到article_body关联表
        ArticleBody articleBody = new ArticleBody();

        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);
        long bodyId = articleBodyMapper.selectMaxId();
        article.setBodyId(bodyId);
        //更新bodyId
        articleMapper.updateBodyId(article.getId(),bodyId);
        Map<String,String> map = new HashMap<>();
        map.put("id",article.getId().toString());
        //返回article的Id
        return Result.success(map);

    }


}
