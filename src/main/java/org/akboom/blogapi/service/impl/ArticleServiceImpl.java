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
import org.akboom.blogapi.vo.ArticleSearchVo;
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
        pageParam.setPage(offset);

        List<ArticleVo> articleVoList = articleMapper.selectArticleByPageParam(pageParam);
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
        boolean isEdit = false;
        //新建与数据库交互article对象
        Article article = new Article();
        /*即将操作的文章Id
         * 对更新和插入操作进行不同的赋值逻辑*/
        Long articleId;
        //id不为空 则执行update
        if (articleParam.getId() != null) {
            articleId = articleParam.getId();
            article.setId(articleId);
            article.setSummary(articleParam.getSummary());
            article.setTitle(articleParam.getTitle());
            article.setCategoryId(articleParam.getCategory().getId());
            articleMapper.updateArticle(article);
            isEdit = true;
        } else {//id为空则执行insert
            articleId = SnowFlakeUtils.nextId();
            article.setId(articleId);
            article.setAuthorId(sysUser.getId());
            article.setCategoryId(articleParam.getCategory().getId());
            article.setCreateDate(System.currentTimeMillis());
            article.setCommentCounts(0);
            article.setSummary(articleParam.getSummary());
            article.setTitle(articleParam.getTitle());
            article.setViewCounts(0);
            article.setWeight(0);
            //插入到article表
            articleMapper.insertArticle(article);
        }
        //添加到article_tag关联表
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            /*如果是更新操作 先删除article的所有tag 再插入*/
            if (isEdit == true) {
                articleTagMapper.deleteTagsByArticleId(articleId);
            }
            /*执行插入操作*/
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(articleId);
                articleTag.setTagId(tag.getId());
                articleTagMapper.insert(articleTag);
            }
        }
        //添加到article_body关联表
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(articleId);
        /*如果是编辑操作 更新body即可*/
        if (isEdit == true) {
            articleBodyMapper.updateBodyByArticleId(articleBody);
        }
        /*新增操作 根据文章id插入文章主体到body表*/
        else articleBodyMapper.insert(articleBody);

        //返回article的Id
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(articleId));
        return Result.success(map);

    }

    @Override
    public Result searchArticlesByWord(String search) {
        List<ArticleSearchVo> result = articleMapper.selectArticlesByWord(search);
        return Result.success(result);
    }


}
